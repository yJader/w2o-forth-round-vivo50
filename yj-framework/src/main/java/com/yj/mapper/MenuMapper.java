package com.yj.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yj.domain.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 11:50:10
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectAllPerms();

    List<String> selectPermsByUserId(Long id);
}

