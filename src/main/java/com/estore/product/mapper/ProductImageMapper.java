/**
 * Copyright(C) 2017 the original author Tan XuJie.
 * All rights reserved.
 */
package com.estore.product.mapper;

import java.util.List;

import com.estore.product.entity.ProductImage;

/**
 * @author Tan XuJie
 *
 */
public interface ProductImageMapper {

    /**
     * 
     * @param entity
     * @return
     */
    int insert(ProductImage entity);

    /**
     * 
     * @param productId
     * @return
     */
    List<ProductImage> selectByProductId(int productId);

    /**
     * 
     * @param productId
     * @return
     */
    List<String> selectImageNamesByProductId(int productId);

    /**
     * 
     * @param ids
     * @return
     */
    int delete(int[] ids);

    /**
     * 
     * @param productId
     * @return
     */
    int deleteByProductId(int productId);
}