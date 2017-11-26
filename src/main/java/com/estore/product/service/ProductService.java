/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.product.service;

import java.util.List;

import com.estore.base.PaginationDto;
import com.estore.base.ResponseResult;
import com.estore.product.dto.ProductDto;
import com.estore.product.dto.ProductListDto;
import com.estore.product.dto.ProductPair;
import com.estore.product.dto.ProductSearchDto;
import com.estore.product.entity.Product;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public interface ProductService {

    /**
     * 保存产品信息
     * 
     * @param product
     */
    ResponseResult save(Product product);

    /**
     * 删除产品信息
     * 
     * @param productId
     */
    void remove(int productId);

    /**
     * 
     * @param product
     */
    void modify(Product product);

    /**
     * 查询产品列表信息一览
     * 
     * @param sdata
     * @return
     */
    PaginationDto<ProductListDto> search(ProductSearchDto sdata);

    PaginationDto<ProductListDto> searchUnderShelf(ProductSearchDto sdata);

    /**
     * 
     * @param sdata
     * @return
     */
    List<ProductPair> searchForApp(ProductSearchDto sdata);

    /**
     * 查询产品详情
     * 
     * @param productId
     * @return
     */
    ProductDto getDetail(int productId);

    boolean changeShelf(int productId);
}