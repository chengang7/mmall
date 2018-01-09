package com.mall.service.impl;

import com.mall.common.Const;
import com.mall.common.ServerResponse;
import com.mall.dao.UserMapper;
import com.mall.entity.User;
import com.mall.service.IUserService;
import com.mall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2018
 *
 * @FileName: UserServiceImpl
 * @Author: root1
 * @Date: 18-1-9 上午10:30
 * @Description: 用户接口实现
 * History:
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        //判断用户名是否存在
        if (resultCount==0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        //登录密码MD5加密
        String md5Password=MD5Util.MD5EncodeUtf8(password);

        User user = userMapper.selectLogin(username,md5Password);
        //为什么要添加这一步验证，当走到这一步时说明用户名是存在的，密码不对。。。
        if (user==null){
            return ServerResponse.createByErrorMessage("密码错误");
        }
        //清空密码
        user.setPassword(StringUtils.EMPTY);


        return ServerResponse.createBySuccess("登陆成功",user);
    }

    /**
     * 需要检验用户名和邮箱是否存在
     * @param user
     * @return
     */
    public ServerResponse<String> register(User user){
        //校验用户名
        int resultCount = userMapper.checkUsername(user.getUsername());
        if (resultCount>0){
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        resultCount=userMapper.checkEmail(user.getEmail());
        if (resultCount>0){
            return ServerResponse.createByErrorMessage("邮箱已存在");
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);

        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        //插入数据
        resultCount=userMapper.insert(user);
        //添加判断
        if (resultCount==0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
            return ServerResponse.createBySuccessMessage("注册成功");
    }
}
