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
public class MajorCategory extends AbstractBaseEntity {
    private static final long serialVersionUID = 9181729838182362289L;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}