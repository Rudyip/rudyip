package com.rudy.controller;

import com.rudy.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeptController {
    @Autowired
    DeptService deptService;
    @RequestMapping("/deptSelect")
    @ResponseBody
    public Object selectAllDept(){
        return deptService.selectAllDept();
    }
}
