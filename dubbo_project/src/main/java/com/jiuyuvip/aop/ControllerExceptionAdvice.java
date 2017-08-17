package com.jiuyuvip.aop;


import com.jiuyuvip.Exception.ApiException;
import com.jiuyuvip.util.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.sasl.AuthenticationException;

/**
 * Created by Administrator on 2017/8/8.
 * Description: *_*
 */
// TODO: 2017/8/9 有待测试
@ControllerAdvice
@ResponseBody
public class ControllerExceptionAdvice {

    @ExceptionHandler(ApiException.class)
    public ResponseWrapper handleApiException(ApiException e) {
        return ResponseWrapper.failed(e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        // TODO: 2017/8/8 add log
        System.out.println("请求参数错误，必须参数不存在"+e.toString());


        return "bad request";
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        System.out.println("请求方法错误非post或非get请求"+e.toString());

        return "method not allowed";
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public String handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {


        return "unsupported media type";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(AuthenticationException e) {


        return "unauthorized";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {


        return "internal server error";
    }
}
