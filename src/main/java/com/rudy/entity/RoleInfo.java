package com.rudy.entity;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Data
public class RoleInfo {
    private int roleId;
    private String roleName;
    private String roleKey;
    private int roleSort;
    private char status;
    private char delFlag;
}
