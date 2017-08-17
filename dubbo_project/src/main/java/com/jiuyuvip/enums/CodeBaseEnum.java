package com.jiuyuvip.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 枚举接口
 * shape = JsonFormat.Shape.OBJECT 用于返回JSON对象
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface CodeBaseEnum {
    int getCode();
    String getDesc();
}