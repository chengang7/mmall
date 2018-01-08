package com.mall.dao;

import com.mall.entity.Order;
/**
 * Copyright (C), 2017-2018
 *
 * @FileName: OrderMapper
 * @Author: root1
 * @Date: 18-1-8 下午3:00
 * @Description: 订单dao层接口
 * History:
 */
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}