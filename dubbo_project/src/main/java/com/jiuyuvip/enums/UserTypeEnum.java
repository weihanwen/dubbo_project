package com.jiuyuvip.enums;

/**
 * Created by Administrator on 2017/4/20.
 */
public enum UserTypeEnum implements CodeBaseEnum {
    SystemUser(0,"平台用户"),Client(1,"客户");

    private final int code;
    private final String desc;

    UserTypeEnum(int code, String desc) {
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
