package com.rudy.controller;

import com.rudy.entity.RoleInfo;
import com.rudy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping("/roleSelect")
    @ResponseBody
    public Object roleSelect(){
        List<RoleInfo> roleInfos = roleService.selectAllRole();
        return roleInfos;
    }
}
