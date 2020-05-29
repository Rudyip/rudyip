package com.rudy.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RoleMenuInfo {
    private Integer roleId;

    private Integer menuId;
}