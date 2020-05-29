package com.rudy.dao;

import com.rudy.entity.DeptInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@Repository
public interface DeptInfoMapper {
    List<DeptInfo> selectAllDept();
    DeptInfo selectDeptByName(String deptName);
}
