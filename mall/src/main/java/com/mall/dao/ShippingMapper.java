package com.mall.dao;

import com.mall.entity.Shipping;
/**
 * Copyright (C), 2017-2018
 *
 * @FileName: ShippingMapper
 * @Author: root1
 * @Date: 18-1-8 下午3:00
 * @Description: 收货地址dao层
 * History:
 */
public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);
}