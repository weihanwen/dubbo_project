package com.jiuyuvip.enums;

/**
 * 用户状态
 * Created by Administrator on 2017/4/20.
 */
public enum UserStatusEnum implements CodeBaseEnum {
    Normall(0,"正常"),Locked(1,"锁定"),Blacklist(2,"拉黑");

    private final int code;
    private final String desc;

    UserStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
