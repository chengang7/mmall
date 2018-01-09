package com.mall.service;

import com.mall.common.ServerResponse;
import com.mall.entity.User;

/**
 * Copyright (C), 2017-2018
 *
 * @FileName: IUserService
 * @Author: root1
 * @Date: 18-1-9 上午10:25
 * @Description: 用户接口
 * History:
 */
public interface IUserService {

    /**
     * 用户登录方法
     * @param username
     * @param password
     * @return
     */
    ServerResponse<User> login(String username, String password);

    /**
     * 用户注册方法
     * @param user
     * @return
     */
    ServerResponse<String> register(User user);
}
