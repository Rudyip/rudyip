package com.rudy.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Data
public class LayUITable {
    private int code;
    private String msg;
    private long count;
//    private List<MyUserInfo> data;
    private List<TableInfo> data;

}
