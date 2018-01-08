package com.mall.dao;

import com.mall.entity.User;
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
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}