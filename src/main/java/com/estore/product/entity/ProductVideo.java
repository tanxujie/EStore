package com.estore.product.entity;

import com.estore.base.AbstractBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductVideo extends AbstractBaseEntity {
    private static final long serialVersionUID = -5883158305699874139L;

    /**
     * 产品ID
     */
    private int productId;

    private String name;

    private int displayOrder;
}