package com.longzai.handler.excption;

import com.longzai.domain.ResponseResult;
import com.longzai.enums.AppHttpCodeEnum;
import com.longzai.exception.SystemException;
import com.longzai.domain.ResponseResult;
import com.longzai.enums.AppHttpCodeEnum;
import com.longzai.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //打印异常信息
        log.error("出现了异常！ {}",e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }


//    @ExceptionHandler(Exception.class)
//    public ResponseResult exceptionHandler(Exception e){
//        //打印异常信息
//        log.error("出现了异常！ {}",e);
//        //从异常对象中获取提示信息封装返回
//        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
//    }

    @ExceptionHandler(ClassCastException.class)
    public ResponseResult ClassCastException(ClassCastException classCastException){
        //打印异常信息
        log.error("出现了异常！ {}",classCastException);
        return ResponseResult.errorResult(AppHttpCodeEnum.NOCONTENT_OPERATOR_AUTH.getCode(),classCastException.getMessage());
    }
}
