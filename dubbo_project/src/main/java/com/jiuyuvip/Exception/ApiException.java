package com.jiuyuvip.Exception;


import com.jiuyuvip.enums.ErrorCodeEnum;

/**
 * Api异常
 * Spring事务管理 需要抛出RuntimeException才会自动回滚
 * Created by Administrator on 2017/5/16.
 */
public class ApiException extends RuntimeException {
    private int code;

    public ApiException(int code, String msg){
        super(msg);
        this.code =code;
    }

    public ApiException(ErrorCodeEnum err){
        super(err.getDesc());
        this.code =err.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
