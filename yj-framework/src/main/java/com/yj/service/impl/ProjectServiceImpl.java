package com.yj.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.constants.SystemConstants;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.Project;
import com.yj.domain.vo.HotProjectVO;
import com.yj.domain.vo.PageVO;
import com.yj.domain.vo.ProjectDetailVO;
import com.yj.domain.vo.ProjectListVO;
import com.yj.mapper.ProjectMapper;
import com.yj.service.ProjectService;
import com.yj.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

/**
 * (Project)表服务实现类
 *
 * @author makejava
 * @since 2023-02-18 11:10:00
 */
@Service("projectService")
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    public ResponseResult<PageVO<HotProjectVO>> hotProjectList(Integer pageNum, Integer pageSize) {
        // 查询热门项目, 封装为ResponseResult返回
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正常发布的项目
        queryWrapper.eq(Project::getStatus, SystemConstants.PROJECT_STATUS_NORMAL);
        // 必须未筹齐
        queryWrapper.apply("current < target");
        // 按照浏览量降序进行排序
        queryWrapper.orderByDesc(Project::getViewCount);
        // TODO 根据用户积分进行排序

        //分页查询
        Page<Project> projectPage = new Page<>(pageNum, pageSize);
        page(projectPage, queryWrapper);

        //封装查询结果
        List<Project> projects = projectPage.getRecords();
        List<HotProjectVO> hotProjectVOS = BeanCopyUtils.copyBeanList(projects, HotProjectVO.class);

        return ResponseResult.okResult(new PageVO<>(hotProjectVOS, projectPage.getTotal()));
    }

    @Override
    public ResponseResult<PageVO<ProjectListVO>> projectList(Integer pageNum, Integer pageSize) {
        // 查询项目列表, 封装为ResponseResult返回
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正常发布的项目
        queryWrapper.eq(Project::getStatus, SystemConstants.PROJECT_STATUS_NORMAL);
        // 按照时间降序进行排序
        queryWrapper.orderByDesc(Project::getCreateTime);

        //分页查询
        Page<Project> projectPage = new Page<>(pageNum, pageSize);
        page(projectPage, queryWrapper);

        //封装查询结果
        List<Project> projects = projectPage.getRecords();
        List<ProjectListVO> projectListVOs = BeanCopyUtils.copyBeanList(projects, ProjectListVO.class);

        return ResponseResult.okResult(new PageVO<>(projectListVOs, projectPage.getTotal()));
    }

    @Override
    public ResponseResult<ProjectDetailVO> getProjectDetail(Long id) {
        Project project = getById(id);
        ProjectDetailVO projectDetailVO = BeanCopyUtils.copyBean(project, ProjectDetailVO.class);
        return ResponseResult.okResult(projectDetailVO);
    }

}