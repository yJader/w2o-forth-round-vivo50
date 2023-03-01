package com.yj.controller;

import com.yj.annotation.SystemLog;
import com.yj.annotation.UpdateViewCount;
import com.yj.constants.SystemConstants;
import com.yj.domain.ResponseResult;
import com.yj.domain.dto.NewProjectDTO;
import com.yj.domain.dto.PageDTO;
import com.yj.domain.dto.SearchProjectDTO;
import com.yj.domain.dto.UpdateProjectDTO;
import com.yj.domain.entity.Project;
import com.yj.domain.vo.PageVO;
import com.yj.domain.vo.projectvo.*;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.exception.SystemException;
import com.yj.service.ProjectService;
import com.yj.utils.BeanCopyUtils;
import com.yj.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Package com.yj.controller
 * @Author yJade
 * @Date 2023-02-18 11:15
 */
@Api(tags = "项目", description = "众筹项目相关接口")
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * TODO 自己的东西比较多 有空测试一下这个接口
     * TODO 改为 未筹齐 / 筹齐时间在xx天内的 项目
     */
    @GetMapping("/hotProjectList")
    @ApiOperation(value = "未筹齐热门项目列表(限前100)")
    public ResponseResult<PageVO<HotProjectVO>> hotProjectList(@Validated PageDTO pageDTO) {
        Integer pageNum = pageDTO.getPageNum();
        Integer pageSize = pageDTO.getPageSize();
        //最多只展示前100名
        if (pageNum*pageSize > SystemConstants.MAX_HOT_PROJECT_LIST) {
            throw new SystemException(AppHttpCodeEnum.THE_NUMBER_OF_QUERIES_IS_TOO_LARGE);
        }
        return projectService.hotProjectList(pageNum, pageSize);
    }

    @GetMapping("/search")
    @ApiOperation(value = "搜索项目")
    public ResponseResult<PageVO<ProjectListVO>> searchProject(@Validated SearchProjectDTO searchProjectDTO) {
        return projectService.searchProject(searchProjectDTO);
    }

    @GetMapping("/list")
    @ApiOperation(value = "项目列表")
    public ResponseResult<PageVO<ProjectListVO>> projectList(@Validated PageDTO pageDTO) {
        return projectService.projectList(pageDTO.getPageNum(), pageDTO.getPageSize());
    }

    @UpdateViewCount
    @GetMapping("/{id}")
    @ApiOperation(value = "项目详情")
    public ResponseResult<ProjectDetailVO> projectDetail(@PathVariable("id") Long id) {
        return projectService.getProjectDetail(id);
    }

    @GetMapping("/myProjectList")
    @ApiOperation(value = "我的项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
    })
    public ResponseResult<PageVO<MyProjectListVO>> myProjectList(@Validated PageDTO pageDTO) {
        return projectService.getMyProjectList(pageDTO.getPageNum(), pageDTO.getPageSize());
    }

    @GetMapping("/myProject/{id}")
    @ApiOperation(value = "我的项目详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
    })
    public ResponseResult<MyProjectDetailVO> myProjectDetail(@PathVariable("id") Long id) {
        return projectService.getMyProjectDetail(id);
    }

    @PutMapping("/{id}")
    @SystemLog(businessName = "修改我的项目")
    @ApiOperation(value = "修改我的项目, 已发布的项目不能修改")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
    })
    public ResponseResult updateProject(@RequestBody @Validated UpdateProjectDTO updateProjectDTO) {
        checkProjectAuth(updateProjectDTO.getId());
        // 验证项目
        Project originalProject = projectService.getById(updateProjectDTO.getId());
        if (originalProject.getStatus().equals(SystemConstants.PROJECT_STATUS_NORMAL)) {
            throw new SystemException(AppHttpCodeEnum.PROJECT_CANNOT_BE_MODIFIED);
        }

        Project project = BeanCopyUtils.copyBean(updateProjectDTO, Project.class);
        return projectService.updateProject(project);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除我的项目, 只能删除未正式发布的项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目id", required = true),
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
    })
    @SystemLog(businessName = "用户删除项目")
    public ResponseResult deleteProject(@PathVariable Long id) {
        // 只能编辑自己的项目
        checkProjectAuth(id);
        return projectService.deleteProject(id);
    }

    @PostMapping("")
    @SystemLog(businessName = "提交新项目")
    @ApiOperation(value = "提交新项目")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
    })
    public ResponseResult submitNewProject(@RequestBody @Validated NewProjectDTO newProjectDTO) {
        
        Project project = BeanCopyUtils.copyBean(newProjectDTO, Project.class);
        return projectService.submitNewProject(project);
    }

    private void checkProjectAuth(Long projectId) {
        Long userId = SecurityUtils.getUserId();
        Long creatorId = projectService.getById(projectId).getCreateBy();
        if (!userId.equals(creatorId)) {
            throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        }
    }

    private void checkProjectStatus(Long projectId) {
        Project project = projectService.getById(projectId);
        if (project.getStatus().equals(SystemConstants.PROJECT_STATUS_NORMAL)) {
            throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        }
    }
}
