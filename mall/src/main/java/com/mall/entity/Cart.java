package com.mall.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
/**
 * Copyright (C), 2017-2018
 *
 * @FileName: CartMapper
 * @Author: root1
 * @Date: 18-1-8 下午3:00
 * @Description: 购物车实体类
 * History:
 */
@Getter@Setter
public class Cart {
    private Integer id;

    private Integer userId;

    private Integer productId;

    private Integer quantity;

    private Integer checked;

    private Date createTime;

    private Date updateTime;

    public Cart(Integer id, Integer userId, Integer productId, Integer quantity, Integer checked, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.checked = checked;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Cart() {
        super();
    }

}