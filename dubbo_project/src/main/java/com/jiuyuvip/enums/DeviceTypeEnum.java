package com.jiuyuvip.enums;

/**
 * 设备类型
 * Created by Administrator on 2017/7/12.
 */
public enum DeviceTypeEnum implements CodeBaseEnum {
    Unknown(0,"Unknown"),
    IOS(1,"IOS"),
    Android(2,"Android");

    private final int code;
    private final String desc;

    DeviceTypeEnum(int code, String desc) {
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
