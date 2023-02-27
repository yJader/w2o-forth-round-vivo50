package com.yj.controller;

import com.yj.annotation.SystemLog;
import com.yj.constants.SystemConstants;
import com.yj.domain.ResponseResult;
import com.yj.domain.dto.LoginUserDTO;
import com.yj.domain.entity.User;
import com.yj.domain.vo.PageVO;
import com.yj.domain.vo.projectvo.ProjectListVO;
import com.yj.service.AdminLoginService;
import com.yj.service.ProjectService;
import com.yj.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 原本想写动态路由的 但是前端不会
 * @Package com.yj.controller
 * @Author yJade
 * @Date 2023-02-25 21:18
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "管理员", description = "管理员相关接口")
public class AdminController {

    @Autowired
    private ProjectService projectService;


    @Autowired
    private AdminLoginService adminLoginService;

    @PostMapping("/login")
    @ApiOperation(value = "管理员登录")
    public ResponseResult adminLogin(@RequestBody LoginUserDTO loginUserDTO) {
        User user = BeanCopyUtils.copyBean(loginUserDTO, User.class);
        return adminLoginService.login(user);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "管理员退出")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
    })
    public ResponseResult adminLogout() {
        return adminLoginService.logout();
    }

    @GetMapping("/projectList")
    @ApiOperation(value = "待审核项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true),
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
    })
    @PreAuthorize("hasAuthority('system:project:examine')")
    public ResponseResult<PageVO<ProjectListVO>> projectList(Integer pageNum, Integer pageSize) {
        return projectService.unauditedProjectList(pageNum, pageSize);
    }

    @PutMapping("/passProject")
    @ApiOperation(value = "项目审核通过")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true),
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
    })
    @PreAuthorize("hasAuthority('system:project:examine')")
    public ResponseResult passProject(Long projectId) {
        return projectService.updateProjectStatus(projectId, SystemConstants.PROJECT_STATUS_NORMAL);
    }

    @PutMapping("/rejectProject")
    @ApiOperation(value = "打回项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true),
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
    })
    @PreAuthorize("hasAuthority('system:project:examine')")
    public ResponseResult rejectProject(Long projectId) {
        return projectService.updateProjectStatus(projectId, SystemConstants.PROJECT_STATUS_DRAFT);
    }

    @DeleteMapping("/deleteProject")
    @ApiOperation(value = "删除项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true),
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
    })
    @SystemLog(businessName = "管理员删除项目")
    @PreAuthorize("hasAuthority('system:project:delete')")
    public ResponseResult deleteProject(Long projectId) {
        // TODO 提交超过xx次就直接删除
        return projectService.deleteProject(projectId);
    }
}
