package com.mall.dao;

import com.mall.entity.OrderItem;
/**
 * Copyright (C), 2017-2018
 *
 * @FileName: OrderItemMapper
 * @Author: root1
 * @Date: 18-1-8 下午3:00
 * @Description: 订单dao层接口
 * History:
 */
public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}