package com.mall.dao;

import com.mall.entity.Category;
/**
 * Copyright (C), 2017-2018
 *
 * @FileName: CategoryMapper
 * @Author: root1
 * @Date: 18-1-8 下午3:00
 * @Description: 类型dao层接口
 * History:
 */
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}