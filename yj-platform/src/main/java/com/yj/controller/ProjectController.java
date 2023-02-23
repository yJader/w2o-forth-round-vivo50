package com.yj.controller;

import com.yj.annotation.SystemLog;
import com.yj.annotation.UpdateViewCount;
import com.yj.constants.SystemConstants;
import com.yj.domain.ResponseResult;
import com.yj.domain.dto.NewProjectDTO;
import com.yj.domain.entity.Project;
import com.yj.domain.vo.*;
import com.yj.domain.vo.projectvo.*;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.exception.SystemException;
import com.yj.service.ProjectService;
import com.yj.utils.BeanCopyUtils;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true),
    })
    public ResponseResult<PageVO<HotProjectVO>> hotProjectList(Integer pageNum, Integer pageSize) {
        //最多只展示前100名
        if (pageNum*pageSize > SystemConstants.MAX_HOT_PROJECT_LIST) {
            throw new SystemException(AppHttpCodeEnum.THE_NUMBER_OF_QUERIES_IS_TOO_LARGE);
        }
        return projectService.hotProjectList(pageNum, pageSize);
    }

    @GetMapping("/projectList")
    @ApiOperation(value = "项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true),
    })
    public ResponseResult<PageVO<ProjectListVO>> projectList(Integer pageNum, Integer pageSize) {
        return projectService.projectList(pageNum, pageSize);
    }

    @UpdateViewCount
    @GetMapping("/projectDetail/{id}")
    @ApiOperation(value = "项目详情")
    public ResponseResult<ProjectDetailVO> getProjectDetail(@PathVariable("id") Long id) {
        return projectService.getProjectDetail(id);
    }

    @GetMapping("/myProject")
    @ApiOperation(value = "我的项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true),
    })
    public ResponseResult<PageVO<MyProjectListVO>> getMyProjectList(Integer pageNum, Integer pageSize) {
        return projectService.getMyProjectList(pageNum, pageSize);
    }

    @GetMapping("/myProjectDetail/{id}")
    @ApiOperation(value = "我的项目详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
    })
    public ResponseResult<MyProjectDetailVO> getMyProjectDetail(@PathVariable("id") Long id) {
        return projectService.getMyProjectDetail(id);
    }

    @PostMapping("/newProject")
    @SystemLog(businessName = "提交新项目")
    @ApiOperation(value = "提交新项目", notes = "文件(图片)上传功能还没做")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
    })
    public ResponseResult submitNewProject(@RequestBody @Validated NewProjectDTO newProjectDTO) {
        // TODO 项目上传功能未完成 还需要在表单中添加图片上传功能(上传一个图片 返回一个OSS存储链接)
        Project project = BeanCopyUtils.copyBean(newProjectDTO, Project.class);
        return projectService.submitNewProject(project);
    }
}
