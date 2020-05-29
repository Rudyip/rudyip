package com.rudy.service;

import com.github.pagehelper.PageInfo;
import com.rudy.entity.MyUserInfo;
import com.rudy.entity.TableInfo;

import java.util.List;
import java.util.Map;

public interface UserService {
    public PageInfo<MyUserInfo> selectUser(int page, int limit);
    public PageInfo<TableInfo> selectUserTableInfos(int page, int limit);
    public List<MyUserInfo> selectAllUser();
    public MyUserInfo selectAllUserByName(String loginName);
    public int insertSelective(MyUserInfo userInfo);
    public MyUserInfo selectUserById(Integer id);
    public int updateById(MyUserInfo userInfo);
    public int deleteById(Integer id);
    public int deleteByIds(List<String> ids);
    public int updateDelFlagById(Integer userId);
    public int updateDelFlagByIds(List<String> ids);
    public Integer selectIdByName(String loginName);
    public int updateByPrimaryKeySelective(MyUserInfo userInfo);
}