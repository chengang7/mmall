package com.mall.dao;

import com.mall.entity.Cart;
/**
 * Copyright (C), 2017-2018
 *
 * @FileName: CartMapper
 * @Author: root1
 * @Date: 18-1-8 下午3:00
 * @Description: 购物车dao层
 * History:
 */
public interface CartMapper {
    /**
     * 根据主键去删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(Cart record);

    /**
     * 根据选择的进行插入
     * @param record
     * @return
     */
    int insertSelective(Cart record);

    /**
     * 根据主键去选择
     * @param id
     * @return
     */
    Cart selectByPrimaryKey(Integer id);

    /**
     * 根据主键去更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Cart record);

    /**
     * 根据主键去更新（有字段）
     * @param record
     * @return
     */
    int updateByPrimaryKey(Cart record);
}