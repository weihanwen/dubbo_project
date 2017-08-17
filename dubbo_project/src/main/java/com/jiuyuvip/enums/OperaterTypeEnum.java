package com.jiuyuvip.enums;

/**
 * Created by Administrator on 2017/4/20.
 */
public enum OperaterTypeEnum implements CodeBaseEnum {
    OPERATE_INSERT(0,"新增"),
    OPERATE_UPDATE(1,"修改"),
    OPERATE_DELETE(2,"删除"),
    OPERATE_QUERY(3,"查询");

    private final int code;
    private final String desc;

    OperaterTypeEnum(int code, String desc) {
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
