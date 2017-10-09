package com.estore.product.mapper;

import java.util.List;

import com.estore.product.entity.ProductVideo;

public interface ProductVideoMapper {

    int insert(ProductVideo entity);
    
    void deleteByProductId(int productId);

    List<ProductVideo> selectByProductId(int productId);
}