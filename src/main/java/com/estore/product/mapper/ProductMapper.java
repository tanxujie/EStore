/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.product.mapper;

import java.util.List;

import com.estore.product.dto.ProductDto;
import com.estore.product.dto.ProductListDto;
import com.estore.product.dto.ProductSearchDto;
import com.estore.product.entity.Product;

/**
 * Product Mapper
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public interface ProductMapper {
    /**
     * 
     * @param entity
     * @return
     */
    int insert(Product entity);

    boolean hasSameCodeProduct(String code);

    /**
     * 
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 
     * @param entity
     * @return
     */
    int update(Product entity);

    /**
     * 
     * @param condition
     * @return
     */
    List<ProductListDto> selectAll(ProductSearchDto condition);

    /**
     * 
     * @param condition
     * @return
     */
    List<ProductListDto> selectAllUnderShelf(ProductSearchDto condition);

    /**
     * 
     * @param condition
     * @return
     */
    List<Product> selectAllForApp(ProductSearchDto condition);

    /**
     * 
     * @param id
     * @return
     */
    ProductDto select(int id);

    Product selectShelf(int id);

    int updateShelf(Product entity);
}