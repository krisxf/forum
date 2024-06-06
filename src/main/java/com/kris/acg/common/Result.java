package com.kris.acg.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Program: acg
 * @Description: 返回结果封装对象
 * @Author: kris
 * @Create: 2023-08-03 17:08
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result{

    /**
     * 信息
     **/
    private String msg;
    /**
     * 状态码
     **/
    private Integer code;

    /**
     * 返回结果
     **/
    private Map<String,Object> data;

    public static Result ok() {
        return new Result("",Code.OK,null);
    }

    public static Result ok(String message) {
        return new Result(message,Code.OK,null);
    }

    public static Result ok(String message,Map<String,Object> data) {
        return new Result(message,Code.OK,data);
    }

    public static Result error(String message) {
        return new Result(message,Code.ERR,null);
    }

    public static Result error(String message,Map<String,Object> data) {
        return new Result(message,Code.ERR,data);
    }
}