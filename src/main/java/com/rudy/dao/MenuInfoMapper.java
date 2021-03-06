package com.rudy.dao;

import com.rudy.entity.MenuInfo;
import com.rudy.entity.MenuTree;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuInfoMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(MenuInfo record);

    int insertSelective(MenuInfo record);

    MenuInfo selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(MenuInfo record);

    int updateByPrimaryKey(MenuInfo record);

    List<MenuInfo> selectAllMenu();
}