package com.rudy.controller;

import com.rudy.dao.MenuInfoMapper;
import com.rudy.entity.MenuTree;
import com.rudy.service.MenuService;
import com.rudy.service.imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("/selectAllMenu")
    @ResponseBody
    public List<MenuTree> selectAllMenu(){
        System.out.println("接收到MenuTree请求！");
        List<MenuTree> menuTrees = menuService.selectAllMenu();
        return menuTrees;
    }
}
