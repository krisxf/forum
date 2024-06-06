package com.kris.acg.exception;

import com.kris.acg.common.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Program: acg
 * @Description: 自定义异常类
 * @Author: kris
 * @Create: 2023-08-04 16:34
 **/

@Getter
@Setter
@AllArgsConstructor
public class BusinessException extends BaseException{
    public BusinessException(String msg){
        this.msg = msg;
        this.code = Code.ERR;
    }
    public BusinessException(String msg,Integer code){
        super(code,msg);
    }
}
