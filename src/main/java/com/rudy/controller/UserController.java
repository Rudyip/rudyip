package com.rudy.controller;

import com.github.pagehelper.PageInfo;
import com.rudy.entity.*;
import com.rudy.service.DeptService;
import com.rudy.service.RoleService;
import com.rudy.service.UserRoleService;
import com.rudy.service.UserService;
import com.rudy.util.LayuiTableUtil;
import com.rudy.util.ShiroEncryption;
import org.apache.ibatis.jdbc.Null;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.sql.Types.NULL;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/Hello")
    @ResponseBody
    public String toHello(Model model){
        List<MyUserInfo> users = userService.selectAllUser();
        model.addAttribute("users",users);
        return "user/hello";
    }

    @RequestMapping("/showLayUiTable")
    public String toShowUserLayui(){
        return "user/hello";
    }
    @RequestMapping("/hello")
    @ResponseBody
    public LayUITable showUserLayui(int page, int limit){
//        PageInfo<MyUserInfo> pageInfo = userService.selectUser(page, limit);
        PageInfo<TableInfo> pageInfo = userService.selectUserTableInfos(page,limit);
        LayuiTableUtil layuiTableUtil = new LayuiTableUtil(0, "返回消息", pageInfo.getTotal(), pageInfo.getList());
        return layuiTableUtil.getLayUITable();
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "user/login";
    }

    @RequestMapping({"/","/toIndex"})
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(String userName, String password, Model model){

        //获取当前用户对象
        Subject subject = SecurityUtils.getSubject();

        //对密码进行加密处理，方便校验
        String pwd = ShiroEncryption.encryption("MD5",password,userName,10);

        //把用户名和密码封装成一个token令牌
        UsernamePasswordToken token = new UsernamePasswordToken(userName, pwd);

        //捕捉登录异常
        try {
            //传入token执行登录操作
            subject.login(token);
            //如果成功返回index页面
            model.addAttribute("msg","登录成功！");
            model.addAttribute("loginUser",userName);
            return "index";
        } catch (UnknownAccountException e) {//捕捉用户名不存在异常
            //如果捕捉到用户名不存在异常返回以下msg，并返回到login页面
            model.addAttribute("msg","用户名不存在!");
            return "user/login";
        }
        catch (IncorrectCredentialsException e){//捕捉密码错误异常
            //如果捕捉到密码错误异常返回以下msg，并返回到login页面
            model.addAttribute("msg","密码不正确!");
            return "user/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(){
        //获取当前用户对象
        Subject subject = SecurityUtils.getSubject();
        //执行注销操作
        subject.logout();
        //最后返回到login页面
        return "user/login";
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public Object insert(String loginName, String userName, String password, String roleName, String deptName,String status, String sex, String phoneNumber, String email){
        Map map = new HashMap();
        MyUserInfo user = userService.selectAllUserByName(loginName);
        String msg;
        if (user != null){
            msg = "插入失败，该用户名已存在！";
            map.put("message",msg);
            map.put("code",0);
            return map;
        }
        char statusChar = '0';
        if (status.equalsIgnoreCase("off")){
            statusChar = '1';
        }
        char sexChar = sex.charAt(0);
        MyUserInfo realUser = new MyUserInfo();
        //根据角色名查询角色
        RoleInfo role = roleService.selectAllRoleByName(roleName);
        DeptInfo dept = deptService.selectDeptByName(deptName);
        String salt = loginName;
        //给用户属性赋值
        realUser.setLoginName(loginName);
        realUser.setUserName(userName);
        realUser.setPassword(password);
        realUser.setPhoneNumber(phoneNumber);
        realUser.setEmail(email);
        realUser.setSex(sexChar);
        realUser.setStatus(statusChar);
        realUser.setSalt(salt);
        realUser.setRoleId(role.getRoleId());
        realUser.setDeptId(dept.getDeptId());
        realUser.setDelFlag('0');
        //System.out.println(realUser);
        UserRoleInfo userRoleInfo = new UserRoleInfo();
        //System.out.println(realUser.getSalt());
        //插入用户信息进用户表
        userService.insertSelective(realUser);
        //接着查询该用户的userId
        int realUserId = userService.selectIdByName(loginName);
        //给userRoleInfo对象赋值
        userRoleInfo.setRoleId(role.getRoleId());
        userRoleInfo.setUserId(realUserId);
        userRoleService.insertUserRole(userRoleInfo);
        msg = "插入成功";
        map.put("message",msg);
        map.put("code",1);
        return map;
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public Object updateUser(Integer userId, String loginName, String userName, String password, String roleName, String deptName,String status, String sex, String phoneNumber, String email){

        Map map = new HashMap();
        MyUserInfo userWithId = userService.selectUserById(userId);
        MyUserInfo userWithName = userService.selectAllUserByName(loginName);
        if (userWithId == null){
            map.put("message","修改失败，该用户ID已不存在！");
            map.put("code",0);
            return map;
        }
        if (userWithName != null && userWithName.getUserId() != userId){
            map.put("message","修改失败，该用户名已存在！");
            map.put("code",0);
            return map;
        }
        userWithId.setUserName(userName);
        userWithId.setLoginName(loginName);
        userWithId.setPhoneNumber(phoneNumber);
        userWithId.setEmail(email);
        char statusChar;
        if (status.equalsIgnoreCase("on")){
            statusChar = '0';
        }
        else {
            statusChar = '1';
        }
        char sexChar = sex.charAt(0);
        userWithId.setSex(sexChar);
        userWithId.setStatus(statusChar);
        RoleInfo role = roleService.selectAllRoleByName(roleName);
        if (role == null){
            Integer fakeRoleId = NULL;
            userWithId.setRoleId(fakeRoleId);
        }
        else {
            userWithId.setRoleId(role.getRoleId());
        }
        DeptInfo dept = deptService.selectDeptByName(deptName);
        if (dept == null){
            Integer fakeDeptId = NULL;
            userWithId.setRoleId(fakeDeptId);
        }
        else {
            userWithId.setDeptId(dept.getDeptId());
        }
        int a = userService.updateByPrimaryKeySelective(userWithId);
        UserRoleInfo userRoleInfo = new UserRoleInfo();
        userRoleInfo.setUserId(userWithId.getUserId());
        userRoleInfo.setRoleId(userWithId.getRoleId());
        int b =userRoleService.updateUserRole(userRoleInfo);
        if (a <= 0 || b <= 0){
            map.put("message","数据库异常，修改失败！");
            map.put("code",0);
            return map;
        }
        map.put("message","修改成功！");
        map.put("code",1);
        return map;
    }

    @RequestMapping("/toolDelete")
    @ResponseBody
    public String toolDelete(Integer userId){
        MyUserInfo user = userService.selectUserById(userId);
        if (user == null){
            return "删除失败，该用户ID不存在！";
        }
        //userService.deleteById(id);
        userService.updateDelFlagById(userId);
        return "删除成功！";
    }

    @RequestMapping("/toolbarDelete")
    @ResponseBody
    public String toolbarDelete(@RequestParam(value = "list")String list){
        List<String> ids = (List<String>) JSON.parse(list);
        //int i = userService.deleteByIds(ids);
        int i = userService.updateDelFlagByIds(ids);
        if (i <= 0){
            return "删除失败";
        }
        return "删除成功！";
    }
}
