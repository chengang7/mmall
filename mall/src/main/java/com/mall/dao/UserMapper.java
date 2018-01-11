package com.mall.dao;

import com.mall.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Copyright (C), 2017-2018
 *
 * @FileName: UserMapper
 * @Author: root1
 * @Date: 18-1-8 下午3:00
 * @Description: 用户dao层
 * History:
 */
public interface UserMapper {
    /**
     * 根据主键来删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 根据选择来插入
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据主键来选择
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 根据选择主键来更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据主键来更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 检查用户名
     * @param username
     * @return
     */
    int checkUsername(String username);

    /**
     * 检查email
     * @param email
     * @return
     */
    int checkEmail(String email);

    /**
     * 查询登录
     * @param username
     * @param password
     * @return
     */
    User selectLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 查询密码查询问题
     * @param username
     * @return
     */
    String selectQuestionByUsername(String username);

    /**
     * 检查密保问题答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    int checkAnswer(@Param("username")String username,@Param("question")String question,@Param("answer")String answer);

    /**
     * 更新用户密码
     * @param username
     * @param passwordNew
     * @return
     */
    int updatePasswordByUsername(@Param("username")String username,@Param("passwordNew")String passwordNew);

    /**
     * 检查密码
     * @param password
     * @param userId
     * @return
     */
    int checkPassword(@Param("username")String password,@Param("userId")Integer userId);

    /**
     * 根据id来更新用户email,不能更新id
     * @param email
     * @param userId
     * @return
     */
    int checkEmailByUserId(@Param("email")String email,@Param("userId")Integer userId);
}