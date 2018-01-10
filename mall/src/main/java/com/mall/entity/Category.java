package com.mall.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
/**
 * Copyright (C), 2017-2018
 *
 * @FileName: Category
 * @Author: root1
 * @Date: 18-1-8 下午3:00
 * @Description: 分类实体类
 * History:
 */
@Getter@Setter
public class Category {
    private Integer id;

    private Integer parentId;

    private String name;

    private Boolean status;

    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;

    public Category(Integer id, Integer parentId, String name, Boolean status, Integer sortOrder, Date createTime, Date updateTime) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.status = status;
        this.sortOrder = sortOrder;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Category() {
        super();
    }


}