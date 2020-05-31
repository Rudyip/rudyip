package com.rudy.controller;

import com.rudy.entity.RoleInfo;
import com.rudy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//角色控制器
@Controller
public class RoleController {
    @Autowired
    RoleService roleService;

    //动态加载角色下拉框数据的请求
    @RequestMapping("/roleSelect")
    @ResponseBody
    public Object roleSelect(){
        List<RoleInfo> roleInfos = roleService.selectAllRole();
        return roleInfos;
    }
}
