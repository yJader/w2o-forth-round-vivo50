package com.yj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.domain.ResponseResult;
import com.yj.domain.dto.SearchProjectDTO;
import com.yj.domain.entity.Project;
import com.yj.domain.vo.*;
import com.yj.domain.vo.projectvo.*;

/**
 * (Project)表服务接口
 *
 * @author makejava
 * @since 2023-02-18 11:09:59
 */
public interface ProjectService extends IService<Project> {

    ResponseResult<PageVO<HotProjectVO>> hotProjectList(Integer pageNum, Integer pageSize);

    ResponseResult<PageVO<ProjectListVO>> projectList(Integer pageNum, Integer pageSize);

    ResponseResult<ProjectDetailVO> getProjectDetail(Long id);

    ResponseResult<PageVO<MyProjectListVO>> getMyProjectList(Integer pageNum, Integer pageSize);

    ResponseResult<MyProjectDetailVO> getMyProjectDetail(Long id);

    ResponseResult submitNewProject(Project project);

    ResponseResult<PageVO<ProjectListVO>> searchProject(SearchProjectDTO searchProjectDTO);
}
