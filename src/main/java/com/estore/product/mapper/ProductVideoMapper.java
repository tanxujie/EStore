package com.estore.product.mapper;

import com.estore.product.entity.ProductVideo;

public interface ProductVideoMapper {

    int insert(ProductVideo entity);
    
    void deleteByProductId(int productId);
}