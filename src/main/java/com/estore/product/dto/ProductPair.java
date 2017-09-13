/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.product.dto;

import com.estore.product.entity.Product;

import lombok.Data;

/**
 * 产品一览页面信息对
 * 
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Data
public class ProductPair {

    /**
     * 产品信息（左侧显示）
     */
    private Product left;

    /**
     * 产品信息（右侧显示）
     */
    private Product right;

    /**
     * 构造器
     */
    public ProductPair(Product left, Product right) {
        this.left = left;
        this.right = right;
    }

    public boolean isHasLeft() {
        return (null != this.left);
    }

    public boolean isHasRight() {
        return (null != this.right);
    }
}
