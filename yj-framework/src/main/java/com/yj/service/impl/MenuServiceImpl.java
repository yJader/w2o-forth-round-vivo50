package com.yj.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.domain.entity.Menu;
import com.yj.mapper.MenuMapper;
import com.yj.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 11:50:10
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}

