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

import com.estore.base.ResponseResult;
import com.estore.product.dto.PreviewConfig;
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
     * @return 
     */
    @Override
    public ResponseResult save(Product product) {
        if (this.productMapper.hasSameCodeProduct(product.getCode())) {
            return new ResponseResult(false, "相同编码的产品已经存在，请输入新编码。"); 
        }
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
        return new ResponseResult(true, "产品信息保存成功");
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

    @Override
    public List<ProductListDto> searchUnderShelf(ProductSearchDto sdata) {
        if (StringUtils.isNotBlank(sdata.getOrderBy())) {
            sdata.setOrderBy("A." + sdata.getOrderBy());
        }
        return this.productMapper.selectAllUnderShelf(sdata);
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
        ProductDto productDto = this.productMapper.select(productId);
        List<ProductImage> productImages = this.productImageMapper.selectByProductId(productId);
        List<String> imageInitialPreview = new ArrayList<>();
        List<PreviewConfig> imagePreviewConfig = new ArrayList<>();
        PreviewConfig previewConfig = null;
        for (ProductImage image : productImages) {
            imageInitialPreview.add("/download/image/" + image.getNewName());
            previewConfig = new PreviewConfig();
            previewConfig.setCaption("");
            previewConfig.setKey(image.getNewName());
            imagePreviewConfig.add(previewConfig);
        }
        productDto.setImageInitialPreview(imageInitialPreview);
        productDto.setImagePreviewConfig(imagePreviewConfig);

        List<ProductVideo> productVideos = this.productVideoMapper.selectByProductId(productId);
        List<String> videoInitialPreview = new ArrayList<>();
        List<PreviewConfig> videoPreviewConfig = new ArrayList<>();
        PreviewConfig videopreviewConfig = null;
        for (ProductVideo video : productVideos) {
            videoInitialPreview.add("/download/image/" + video.getName());
            
            videopreviewConfig = new PreviewConfig();
            videopreviewConfig.setCaption("");
            videopreviewConfig.setKey(video.getName());
            videoPreviewConfig.add(videopreviewConfig);
        }
        productDto.setVideoInitialPreview(videoInitialPreview);
        productDto.setVideoPreviewConfig(videoPreviewConfig);

        return productDto;
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
                    FileUtils.forceDelete(new File(Constants.IMAGE_DIR_PATH + imageName));
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

        this.productImageMapper.deleteByProductId(product.getId());
        String[] imageNames = product.getImageNames();
        if (ArrayUtils.isNotEmpty(imageNames)) {
            int displayOrder = 1;
            ProductImage productImage = null;
            for (String imageName : imageNames) {
                if (StringUtils.substringBeforeLast(imageName, ".").length() != 36) {
                    continue;
                }
                productImage = new ProductImage();
                productImage.setProductId(product.getId());
                productImage.setNewName(imageName);
                productImage.setDisplayOrder(displayOrder);
                this.productImageMapper.insert(productImage);
                displayOrder++;
            }
        }

        this.productVideoMapper.deleteByProductId(product.getId());
        if (StringUtils.isNotBlank(product.getVideoName())) {
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