package com.mall.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
/**
 * Copyright (C), 2017-2018
 *
 * @FileName: Order
 * @Author: root1
 * @Date: 18-1-8 下午3:00
 * @Description: 订单实体类
 * History:
 */
@Getter@Setter
public class Order {
    private Integer id;

    private Long orderNo;

    private Integer userId;

    private Integer shippingId;

    private BigDecimal payment;

    private Integer paymentType;

    private Integer postage;

    private Integer status;

    private Date paymentTime;

    private Date sendTime;

    private Date endTime;

    private Date closeTime;

    private Date createTime;

    private Date updateTime;

    public Order(Integer id, Long orderNo, Integer userId, Integer shippingId, BigDecimal payment, Integer paymentType, Integer postage, Integer status, Date paymentTime, Date sendTime, Date endTime, Date closeTime, Date createTime, Date updateTime) {
        this.id = id;
        this.orderNo = orderNo;
        this.userId = userId;
        this.shippingId = shippingId;
        this.payment = payment;
        this.paymentType = paymentType;
        this.postage = postage;
        this.status = status;
        this.paymentTime = paymentTime;
        this.sendTime = sendTime;
        this.endTime = endTime;
        this.closeTime = closeTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Order() {
        super();
    }


}