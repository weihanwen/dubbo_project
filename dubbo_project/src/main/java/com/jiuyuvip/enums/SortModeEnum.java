package com.jiuyuvip.enums;

/**
 * Created by Administrator on 2017/4/21.
 */
public enum SortModeEnum implements CodeBaseEnum {
    ASC(0,"ASC"),
    DESC(1,"DESC");

    private final int code;
    private final String desc;

    SortModeEnum(int code, String desc) {
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
