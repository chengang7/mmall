package com.mall.service.impl;

import com.mall.common.Const;
import com.mall.common.ServerResponse;
import com.mall.common.TokenCache;
import com.mall.dao.UserMapper;
import com.mall.entity.User;
import com.mall.service.IUserService;
import com.mall.util.MD5Util;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.UUID;

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
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        //判断用户名是否存在
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        //登录密码MD5加密
        String md5Password = MD5Util.MD5EncodeUtf8(password);

        User user = userMapper.selectLogin(username, md5Password);
        //为什么要添加这一步验证，当走到这一步时说明用户名是存在的，密码不对。。。
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        //清空密码
        user.setPassword(StringUtils.EMPTY);


        return ServerResponse.createBySuccess("登陆成功", user);
    }

    /**
     * 需要检验用户名和邮箱是否存在
     *
     * @param user
     * @return
     */
    @Override
    public ServerResponse<String> register(User user) {
        //校验用户名
        ServerResponse validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }

        //校验邮箱
        validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);

        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        //插入数据
        int resultCount = userMapper.insert(user);
        //添加判断
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    /**
     * 校验方法
     *
     * @param str
     * @param type
     * @return
     */
    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            //开始校验
            if (Const.USERNAME.equals(type)) {
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if (Const.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("邮箱已存在");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    /**
     * 忘记密码提示问题
     *
     * @param username
     * @return
     */
    @Override
    public ServerResponse<String> selectQuestion(String username) {
        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isNotBlank(question)) {
            return ServerResponse.createBySuccess(question);
        }
        return ServerResponse.createByErrorMessage("找回密码的问题是空的");
    }

    /**
     * 校验问题答案
     *
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        int resultCount = userMapper.checkAnswer(username, question, answer);
        //resultCount>0说明这个问题及问题的答案，且是正确的
        if (resultCount > 0) {
            //声明一个token出来，用Java里面的UUID
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("答案错误");
    }

    /**
     * 忘记密码修改密码
     *
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @Override
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createByErrorMessage("参数错误，token需要传递");
        }
        //需要校验下用户名是否为空，因为TokenCache.TOKEN_PREFIX+username,forgetToken
        //username可以是空的，取到后面的forgetToken
        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage("token无效或过期了");
        }
        if (StringUtils.equals(forgetToken, token)) {
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount = userMapper.updatePasswordByUsername(username, md5Password);
            //生效行数的判断
            if (rowCount > 0) {
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        } else {
            return ServerResponse.createByErrorMessage("token错误，请重新获取重置密码的token");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    /**
     * 登陆后重置密码
     *
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        //防止横向越权，要校验下这个用户的旧密码，一定要指定是这个用户，因为我们会查询count（1）,如果不指定id,结果就是true(count>0)
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getId());
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码失败错误");
    }

    /**
     * 更新个人信息
     *
     * @param user
     * @return
     */
    @Override
    public ServerResponse<User> updateInformation(User user) {
        //username是不能被更新的
        //email也要进行校验，校验新的email是不是已经存在，并且存在的Email如果相同的话，不能是我们当前用户的
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(), user.getId());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("Email已存在，请重新输入！");
        }
        //创建一个只用于更新的用户
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        //动态更新
        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);

        if (updateCount > 0) {
            return ServerResponse.createBySuccess("更新成功", updateUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败！");
    }

    public ServerResponse<User> getInformation(Integer userId){
        User user = userMapper.selectByPrimaryKey(userId);
        if (user==null){
            return ServerResponse.createByErrorMessage("找不到该用户");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }


}
