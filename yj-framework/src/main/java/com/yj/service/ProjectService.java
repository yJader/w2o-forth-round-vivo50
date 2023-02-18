package com.yj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.Project;
import com.yj.domain.vo.HotProjectVO;
import com.yj.domain.vo.PageVO;
import com.yj.domain.vo.ProjectDetailVO;
import com.yj.domain.vo.ProjectListVO;

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
}
