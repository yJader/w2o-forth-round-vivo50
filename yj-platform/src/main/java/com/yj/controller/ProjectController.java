package com.yj.controller;

import com.yj.constants.SystemConstants;
import com.yj.domain.ResponseResult;
import com.yj.domain.vo.HotProjectVO;
import com.yj.domain.vo.PageVO;
import com.yj.domain.vo.ProjectDetailVO;
import com.yj.domain.vo.ProjectListVO;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.exception.SystemException;
import com.yj.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/hotProjectList")
    @ApiOperation(value = "未筹齐热门项目列表")
    public ResponseResult<PageVO<HotProjectVO>> hotProjectList(Integer pageNum, Integer pageSize) {
        if (pageNum*pageSize > SystemConstants.MAX_HOT_PROJECT_LIST) {
            throw new SystemException(AppHttpCodeEnum.THE_NUMBER_OF_QUERIES_IS_TOO_LARGE);
        }
        return projectService.hotProjectList(pageNum, pageSize);
    }

    @GetMapping("/projectList")
    @ApiOperation(value = "项目列表")
    public ResponseResult<PageVO<ProjectListVO>> projectList(Integer pageNum, Integer pageSize) {
        return projectService.projectList(pageNum, pageSize);
    }

    @GetMapping("/project/{id}")
    @ApiOperation(value = "项目详情")
    public ResponseResult<ProjectDetailVO> getProjectDetail(@PathVariable("id") Long id) {
        return projectService.getProjectDetail(id);
    }
}
