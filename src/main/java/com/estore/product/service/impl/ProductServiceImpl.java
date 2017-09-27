/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.product.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estore.product.dto.ProductDto;
import com.estore.product.dto.ProductListDto;
import com.estore.product.dto.ProductPair;
import com.estore.product.dto.ProductSearchDto;
import com.estore.product.entity.Product;
import com.estore.product.entity.ProductImage;
import com.estore.product.entity.ProductVideo;
import com.estore.product.mapper.ProductImageMapper;
import com.estore.product.mapper.ProductMapper;
import com.estore.product.mapper.ProductVideoMapper;
import com.estore.product.service.ProductService;
import com.estore.utils.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Service("productService")
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductImageMapper productImageMapper;

    @Autowired
    private ProductVideoMapper productVideoMapper;

    /**
     * 保存新的产品等相关信息
     */
    @Override
    public void save(Product product) {
        this.productMapper.insert(product);

        String[] imageNames = product.getImageNames();
        if (ArrayUtils.isNotEmpty(imageNames)) {
            int displayOrder = 1;
            for (String imageName : imageNames) {
                ProductImage productImage = new ProductImage();
                productImage.setProductId(product.getId());
                productImage.setNewName(imageName);
                productImage.setDisplayOrder(displayOrder);
                this.productImageMapper.insert(productImage);
                displayOrder++;
            }
        }

        if (StringUtils.isNotBlank(product.getVideoName())) {
            ProductVideo productVideo = new ProductVideo();
            productVideo.setProductId(product.getId());
            productVideo.setDisplayOrder(1);
            productVideo.setName(product.getVideoName());
            this.productVideoMapper.insert(productVideo);
        }
    }

    /**
     * 
     */
    @Override
    public List<ProductListDto> search(ProductSearchDto sdata) {
        if (StringUtils.isNotBlank(sdata.getOrderBy())) {
            sdata.setOrderBy("A." + sdata.getOrderBy());
        }
        return this.productMapper.selectAll(sdata);
    }

    /**
     * 手机端产品信息查询
     */
    @Override
    public List<ProductPair> searchForApp(ProductSearchDto sdata) {
        if (StringUtils.isNotBlank(sdata.getOrderBy())) {
            sdata.setOrderBy("A." + sdata.getOrderBy());
        }

        // reform to pair
        List<ProductPair> list = new ArrayList<>();

        List<Product> results = this.productMapper.selectAllForApp(sdata);
        if (CollectionUtils.isNotEmpty(results)) {
            for (Product result : results) {
                result.setAuthToken(sdata.getAuthToken());
                List<String> imageNames = this.productImageMapper.selectImageNamesByProductId(result.getId());
                if (CollectionUtils.isNotEmpty(imageNames)) {
                    result.setImageNames(imageNames.toArray(new String[0]));
                }
            }

            int count = results.size();
            for (int i = 0; i < count; i += 2) {
                list.add(new ProductPair(results.get(i), (i+1 < count ? results.get(i + 1) : null)));
            }
        }

        return list;
    }

    /**
     * 
     */
    @Override
    public ProductDto getDetail(int productId) {
        return this.productMapper.select(productId);
    }

    /**
     * 
     */
    @Override
    public void remove(int productId) {
        List<String> productImageNames = this.productImageMapper.selectImageNamesByProductId(productId);
        if (CollectionUtils.isNotEmpty(productImageNames)) {
            for (String imageName : productImageNames) {
                try {
                    FileUtils.forceDelete(new File(Constants.PRODUCT_IMAGE_DIR_PATH + imageName));
                } catch (IOException e) {
                    log.error("文件删除失败", e);
                    continue;
                }
            }
        }
        this.productMapper.delete(productId);
        this.productImageMapper.deleteByProductId(productId);
    }

    /**
     * 
     */
    @Override
    public void modify(Product product) {
        this.productMapper.update(product);

        String[] imageNames = product.getImageNames();
        if (ArrayUtils.isNotEmpty(imageNames)) {
            this.productImageMapper.deleteByProductId(product.getId());
            int displayOrder = 1;
            for (String imageName : imageNames) {
                ProductImage productImage = new ProductImage();
                productImage.setProductId(product.getId());
                productImage.setNewName(imageName);
                productImage.setDisplayOrder(displayOrder);
                this.productImageMapper.insert(productImage);
                displayOrder++;
            }
        }

        if (StringUtils.isNotBlank(product.getVideoName())) {
            this.productVideoMapper.deleteByProductId(product.getId());
            ProductVideo productVideo = new ProductVideo();
            productVideo.setProductId(product.getId());
            productVideo.setDisplayOrder(1);
            productVideo.setName(product.getVideoName());
            this.productVideoMapper.insert(productVideo);
        }
    }

    @Override
    public boolean changeShelf(int productId) {
        Product entity = this.productMapper.selectShelf(productId);
        if (null == entity) {
            return false;
        }
        entity.setUnderShelf(!entity.isUnderShelf());
        return (this.productMapper.updateShelf(entity) == 1);
    }
}