package com.rudy.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RoleTableInfo {
    private Integer roleId;
    private String roleName;
    private String roleKey;
    private Integer sort;
    private char status;
    private char delFlag;
}
