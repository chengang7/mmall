package com.mall.common;

/**
 * Copyright (C), 2017-2018
 *
 * @FileName: ResponseCode
 * @Author: root1
 * @Date: 18-1-9 上午11:12
 * @Description: 把status用到的常量和魔法数值进行一个归类。。。
 * History:
 */
public enum ResponseCode {

    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;


    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
