/**
 * Copyright(C) 2017 the original author Tan XuJie.
 * All rights reserved.
 */
package com.estore.product.dto;

import com.estore.base.AbstractBaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Tan XuJie
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Deprecated
public class ProductImageDto extends AbstractBaseDto {
    private static final long serialVersionUID = 7682877943684601127L;

    /**
     * 产品ID
     */
    private int productId;

    /**
     * 显示顺序
     */
    private int displayOrder;

    /**
     * 包含路径全称
     */
    private String fullName;

    /**
     * 描述
     */
    private String description;
}
