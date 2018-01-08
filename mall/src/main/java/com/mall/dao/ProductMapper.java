package com.mall.dao;

import com.mall.entity.Product;
/**
 * Copyright (C), 2017-2018
 *
 * @FileName: ProductMapper
 * @Author: root1
 * @Date: 18-1-8 下午3:00
 * @Description: 商品dao层接口
 * History:
 */
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}