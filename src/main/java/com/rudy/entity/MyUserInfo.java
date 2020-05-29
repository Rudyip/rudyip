package com.rudy.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MyUserInfo {
    private Integer userId;
    private Integer deptId;
    private String loginName;
    private String userName;
    private String email;
    private String phoneNumber;
    private char sex;
    private String avatar;
    private String password;
    private String salt;
    private char status;
    private char delFlag;
    private Integer roleId;
}
