/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.product.entity;

import com.estore.base.AbstractBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 产品图片
 * 
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductImage extends AbstractBaseEntity {
    private static final long serialVersionUID = 2242556349394003358L;

    /**
     * 产品ID
     */
    private int productId;

    /**
     * 旧名称
     */
    private String oldName;

    /**
     * 新名称
     */
    private String newName;

    /**
     * 描述
     */
    private String description;

    /**
     * 显示顺序
     */
    private int displayOrder;
}