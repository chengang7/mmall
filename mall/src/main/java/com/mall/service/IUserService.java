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

    /**
     * 校验方法
     * @param str
     * @param type
     * @return
     */
    ServerResponse<String> checkValid(String str, String type);

    /**
     * 找回密码问题
     * @param username
     * @return
     */
    ServerResponse<String> selectQuestion(String username);

    /**
     * 校验答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    ServerResponse<String> checkAnswer(String username,String question,String answer);

    /**
     * 忘记密码修改密码
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);
}
