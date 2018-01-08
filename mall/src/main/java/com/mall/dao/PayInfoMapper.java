package com.mall.dao;

import com.mall.entity.PayInfo;
/**
 * Copyright (C), 2017-2018
 *
 * @FileName: PayInfoMapper
 * @Author: root1
 * @Date: 18-1-8 下午3:00
 * @Description: 支付dao层接口
 * History:
 */
public interface PayInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayInfo record);

    int insertSelective(PayInfo record);

    PayInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayInfo record);

    int updateByPrimaryKey(PayInfo record);
}