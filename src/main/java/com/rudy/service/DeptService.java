package com.rudy.service;

import com.rudy.entity.DeptInfo;
import org.springframework.stereotype.Component;

import java.util.List;

public interface DeptService {
    public List<DeptInfo> selectAllDept();
    public DeptInfo selectDeptByName(String deptName);
}
