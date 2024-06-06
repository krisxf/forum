package com.kris.acg.exception;

import com.kris.acg.common.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Program: acg
 * @Description: 抽象异常类
 * @Author: kris
 * @Create: 2023-08-04 16:35
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseException extends RuntimeException{
    Integer code;
    String msg;

    public Result asResult(){
        return new Result(msg,code,null);
    }
}
