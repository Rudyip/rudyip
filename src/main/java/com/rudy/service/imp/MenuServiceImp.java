package com.rudy.service.imp;

import com.rudy.dao.MenuInfoMapper;
import com.rudy.entity.MenuInfo;
import com.rudy.entity.MenuTree;
import com.rudy.service.MenuService;
import com.rudy.util.MenuTreeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImp implements MenuService {
    @Autowired
    private MenuInfoMapper menuInfoMapper;
    //查询所有菜单，并组装成tree格式
    @Override
    public List<MenuTree> selectAllMenu() {
        List<MenuInfo> menus = menuInfoMapper.selectAllMenu();
        return MenuTreeBuilder.getChildPerms(menus,0);
    }
}
