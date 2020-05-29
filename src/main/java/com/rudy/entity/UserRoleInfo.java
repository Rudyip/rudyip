package com.rudy.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserRoleInfo {
    private Integer userId;

    private Integer roleId;
}