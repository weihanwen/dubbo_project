package com.jiuyuvip.enums;

/**
 * Created by Administrator on 2017/4/20.
 */
public enum GenderEnum implements CodeBaseEnum {
    Unknown(-1,"未知"),
    Male(0,"先生"),
    Female(1,"女士");

    private final int code;
    private final String desc;

    GenderEnum(int code, String desc) {
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
