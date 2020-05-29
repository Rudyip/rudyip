package com.rudy.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DeptInfo {
    private Integer deptId;
    private String deptName;
    private Integer orderNum;
    private char status;
    private char delFlag;
}
