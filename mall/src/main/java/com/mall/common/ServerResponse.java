package com.mall.common;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2018
 *
 * @FileName: ServerResponse
 * @Author: root1
 * @Date: 18-1-9 上午10:34
 * @Description: 通用的数据端的响应对象
 * History:
 */
/**
 * 使用jackson进行序列化时，往往会遇到后台某个实体对象的属性为null，
 * 当序列化成json时对应的属性也为null；这样在某些前端组件上应用该json对象会报错。
 *
 *将该标记放在属性上，如果该属性为NULL则不参与序列化 ；如果放在类上边,那对这个类的全部属性起作用
 *保证序列化json的时候，如果是null的对象，key也会消失
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {

    /**
     *
     * 状态b
     */
    private int status;
    /**
     * 信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    /**
     * 私有构造器，方便调用
     *
     * @param status
     */
    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg,T data) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 有个小问题，当我们的T的类型是string时，它会调用的是
     * T data 还是 String msg ，验证后证明是后者
     * 但如果我的T 的类型就是string就要封装到data里面该怎么办？
     *
     * @param status
     * @param msg
     */
    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * 封装一个方法，来判断这个响应是不是正确的响应
     * JsonIgnore使isSuccess()方法不在序列化当中
     * @return
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return status;
    }

    public T getData(){
        return data;
    }

    public String getMsg(){
        return msg;
    }

    /**
     * 创建成功的方法
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }
    public static <T> ServerResponse<T> createBySuccess(String msg, T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    /**
     * 创建失败的方法
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    /**
     * 在很多时候需要暴露一个对外登录的，如果参数校验错误需要
     * 提示错误的方法
     * @param errorCode
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }
}
