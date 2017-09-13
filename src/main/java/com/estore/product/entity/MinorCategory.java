/**
 * Copyright(C) 2017 the original author Tan XuJie.
 * All rights reserved.
 */
package com.estore.product.entity;

import com.estore.base.AbstractBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Tan XuJie
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MinorCategory extends AbstractBaseEntity {
    private static final long serialVersionUID = 8505429705089409103L;

    /**
     * 大分类ID
     */
    private int majorCategoryId;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}