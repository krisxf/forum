package com.kris.acg.handler;

import com.kris.acg.common.Result;

import com.kris.acg.exception.BusinessException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Program: acg
 * @Description: 全局异常处理器
 * @Author: kris
 * @Create: 2023-08-03 17:05
 **/

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 获取日志处理对象
     */
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public Result businessExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        BusinessException exception = (BusinessException) e;
//        记录异常信息：请求的URL，异常信息
        logger.error("Request URL : {}，Exception : {}", request.getRequestURL(),exception.getMsg());

//        当标识了状态码的时候就不拦截
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

//        将记录的异常信息返回到error页面
        Map<String,Object> map = new HashMap<>();
        map.put("url",request.getRequestURL());
        map.put("exception",exception.getMsg());
        return Result.error(exception.getMsg(),map);
    }
    @ExceptionHandler(value={MaxUploadSizeExceededException.class})
    public Result doMaxUploadSizeExceededException(MaxUploadSizeExceededException ex){
        return Result.error("文件大小超出限制");
    }

    @ExceptionHandler(value={RuntimeException.class})
    public Result doRuntimeException(RuntimeException ex){
        logger.error(ex.getMessage());
        return Result.error("未知错误，请刷新重试");
    }

    @ExceptionHandler(value={Exception.class})
    public Result doException(Exception ex){
        logger.error(ex.getMessage());
        return Result.error("未知异常，请刷新重试");
    }

}
