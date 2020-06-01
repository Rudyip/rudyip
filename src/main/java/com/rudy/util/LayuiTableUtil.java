package com.rudy.util;

import com.rudy.entity.LayUITable;
import com.rudy.entity.UserTableInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/***
 * LayUITableUtil工具类
 */
public class LayuiTableUtil {
    private LayUITable layUITable;
    private int code;
    private String msg;
    private long count;
    List<?> data;

    public LayuiTableUtil(int code, String msg, long count, List<?> data) {
        layUITable = new LayUITable();
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public LayUITable getLayUITable(){
        layUITable.setCode(code);
        layUITable.setMsg(msg);
        layUITable.setCount(count);
        layUITable.setData(data);
        return layUITable;
    }
}
