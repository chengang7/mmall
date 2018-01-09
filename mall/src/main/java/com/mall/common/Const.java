package com.mall.common;

/**
 * Copyright (C), 2017-2018
 *
 * @FileName: Const
 * @Author: root1
 * @Date: 18-1-9 下午5:43
 * @Description: 这是一个常量类
 * History:
 */
public class Const {
    public static final String CURRENT_USER="currentUser";

    public interface Role{
        //普通用户
        int ROLE_CUSTOMER=0;
        //管理员
        int ROLE_ADMIN=1;
    }
}
