package com.yj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.constants.RedisKeyConstants;
import com.yj.constants.SystemConstants;
import com.yj.domain.ResponseResult;
import com.yj.domain.dto.SearchProjectDTO;
import com.yj.domain.entity.LoginUser;
import com.yj.domain.entity.Project;
import com.yj.domain.vo.PageVO;
import com.yj.domain.vo.projectvo.*;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.exception.SystemException;
import com.yj.mapper.ProjectMapper;
import com.yj.service.ProjectService;
import com.yj.utils.BeanCopyUtils;
import com.yj.utils.PageUtil;
import com.yj.utils.RedisCache;
import com.yj.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * (Project)表服务实现类
 *
 * @author makejava
 * @since 2023-02-18 11:10:00
 */
@Service("projectService")
@Slf4j
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Autowired
    private RedisCache redisCache;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public ResponseResult<PageVO<HotProjectVO>> hotProjectList(Integer pageNum, Integer pageSize) {
        // 查询热门项目, 封装为ResponseResult返回
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正常发布的项目
        queryWrapper.eq(Project::getStatus, SystemConstants.PROJECT_STATUS_NORMAL);
        // 必须未筹齐
        queryWrapper.apply("current < target");
        // 复合排序
        List<Project> projectList = projectMapper.selectList(queryWrapper);
        for (Project project : projectList) {
            // 将redis中存储的创建者的累积积分取出 并加权到viewCount上 这样可行吗(代码规范层面)
            project.setViewCount(getViewCount(project.getId()) + getCumulativePoints(project.getCreateBy()) * 5L);
        }

        //分页
        PageVO<Project> projectPageVO = PageUtil.listConvertToPage(pageNum, pageSize, projectList, new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return (int) (o2.getViewCount() - o1.getViewCount());
            }
        });

        for (Project row : projectPageVO.getRows()) {
            log.info("{}浏览量为{}", row.getTitle(), row.getViewCount());
        }

        PageVO<HotProjectVO> hotProjectVOPageVO = new PageVO<>();
        hotProjectVOPageVO.setRows(BeanCopyUtils.copyBeanList(projectPageVO.getRows(), HotProjectVO.class));
        hotProjectVOPageVO.setPageTotal(projectPageVO.getPageTotal());
        hotProjectVOPageVO.setTotal(projectPageVO.getTotal());

        return ResponseResult.okResult(hotProjectVOPageVO);
    }

    @Override
    public ResponseResult<PageVO<ProjectListVO>> projectList(Integer pageNum, Integer pageSize) {
        // 查询项目列表, 封装为ResponseResult返回
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正常发布的项目
        queryWrapper.eq(Project::getStatus, SystemConstants.PROJECT_STATUS_NORMAL);
        // 按照时间降序进行排序
        queryWrapper.orderByDesc(Project::getCreateTime);

        return projectListByWrapper(pageNum, pageSize, queryWrapper);
    }

    @Override
    public ResponseResult<PageVO<ProjectListVO>> unauditedProjectList(Integer pageNum, Integer pageSize) {
        // 查询项目列表, 封装为ResponseResult返回
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是未审核的项目
        queryWrapper.eq(Project::getStatus, SystemConstants.PROJECT_STATUS_UNAUDITED);
        // 按照时间降序进行排序
        queryWrapper.orderByDesc(Project::getCreateTime);


        return projectListByWrapper(pageNum, pageSize, queryWrapper);
    }

    @Override
    public ResponseResult updateProjectStatus(Long projectId, String status) {
        LambdaUpdateWrapper<Project> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Project::getId, projectId);
        updateWrapper.set(Project::getStatus, status);
        return null;
    }

    @Override
    public ResponseResult deleteProject(Long projectId) {
        removeById(projectId);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateProject(Project project) {
        updateById(project);
        return ResponseResult.okResult();
    }


    private ResponseResult<PageVO<ProjectListVO>> projectListByWrapper(Integer pageNum, Integer pageSize, LambdaQueryWrapper<Project> queryWrapper) {
        //分页查询
        Page<Project> projectPage = new Page<>(pageNum, pageSize);
        page(projectPage, queryWrapper);

        //封装查询结果
        List<Project> projects = projectPage.getRecords();
        List<ProjectListVO> projectListVOs = BeanCopyUtils.copyBeanList(projects, ProjectListVO.class);

        return ResponseResult.okResult(new PageVO<>(projectListVOs, projectPage.getTotal(), projectPage.getPages()));
    }

    @Override
    public ResponseResult<ProjectDetailVO> getProjectDetail(Long id) {
        Project project = getById(id);
        if (Objects.isNull(project)) {
            throw new SystemException(AppHttpCodeEnum.NOT_FOUND);
        }
        ProjectDetailVO projectDetailVO = BeanCopyUtils.copyBean(project, ProjectDetailVO.class);
        return ResponseResult.okResult(projectDetailVO);
    }

    @SuppressWarnings("AlibabaRemoveCommentedCode")
    @Override
    public ResponseResult<PageVO<MyProjectListVO>> getMyProjectList(Integer pageNum, Integer pageSize) {
        //获取用户id
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUser().getId();

        //查询用户创建的项目
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Project::getCreateBy, userId);
        // 按照时间降序进行排序
        queryWrapper.orderByDesc(Project::getCreateTime);

        Page<Project> projectPage = new Page<>(pageNum, pageSize);
        page(projectPage, queryWrapper);

        List<Project> pageRecords = projectPage.getRecords();
        for (Project pageRecord : pageRecords) {
            //还是for顺手
            pageRecord.setViewCount(getViewCount(pageRecord.getId()));
        }
        //给大伙整个活
        //List<MyProjectListVO> myProjectListVOS = BeanCopyUtils.copyBeanList(pageRecords.stream().map(project -> project.setViewCount(getViewCount(project.getId()))).collect(Collectors.toList()), MyProjectListVO.class);
        List<MyProjectListVO> myProjectListVOS = BeanCopyUtils.copyBeanList(pageRecords, MyProjectListVO.class);

        return ResponseResult.okResult(new PageVO<>(myProjectListVOS, projectPage.getTotal(), projectPage.getPages()));
    }

    @Override
    public ResponseResult<MyProjectDetailVO> getMyProjectDetail(Long id) {
        Project project = getById(id);
        if (Objects.isNull(project)) {
            throw new SystemException(AppHttpCodeEnum.NOT_FOUND);
        }
        project.setViewCount(project.getId());
        MyProjectDetailVO myProjectDetailVO = BeanCopyUtils.copyBean(project, MyProjectDetailVO.class);
        return ResponseResult.okResult(myProjectDetailVO);
    }

    @Override
    public ResponseResult submitNewProject(Project project) {
        //数据校验由javax.validation.constraints.*完成
        //mybatis plus完成内容填充
        save(project);
        redisCache.addCacheMapKeyValue(RedisKeyConstants.PROJECT_VIEW_COUNT, project.getId().toString(), 0);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<PageVO<ProjectListVO>> searchProject(SearchProjectDTO searchProjectDTO) {
        if (!searchProjectDTO.notNull()) {
            throw new SystemException(AppHttpCodeEnum.NO_QUERY_CRITERIA_ENTERED);
        }
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(searchProjectDTO.getProjectTitle()), Project::getTitle, searchProjectDTO.getProjectTitle());
        queryWrapper.like(StringUtils.hasText(searchProjectDTO.getUserId()), Project::getCreateBy, searchProjectDTO.getUserId());
        List<Project> projectList = list(queryWrapper);

        //未搜索出结果
        if (projectList.isEmpty()) {
            throw new SystemException(AppHttpCodeEnum.QUERY_RESULT_IS_EMPTY);
        }

        List<ProjectListVO> projectListVOS = BeanCopyUtils.copyBeanList(projectList, ProjectListVO.class);
        return ResponseResult.okResult(projectListVOS);
    }

    private Long getViewCount(Long articleId) {
        Integer viewCount = redisCache.getCacheMapValue(RedisKeyConstants.PROJECT_VIEW_COUNT, articleId.toString());
        return viewCount.longValue();
    }

    private Integer getCumulativePoints(Long userId) {
        Integer cumulativePoints = redisCache.getCacheMapValue(RedisKeyConstants.USER_CUMULATIVE_POINTS, userId.toString());
        return cumulativePoints;
    }
}