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

import com.estore.base.PaginationDto;
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
import com.estore.utils.ImageUtils;

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

        String[] imageNames = ImageUtils.removeDuplicate(product.getImageNames());
        if (ArrayUtils.isNotEmpty(imageNames)) {
            int displayOrder = 1;
            ProductImage productImage = null;
            String[] imageFileNames = null;
            for (String imageName : imageNames) {
                if (StringUtils.isBlank(imageName)) {
                    continue;
                }
                imageFileNames = StringUtils.split(imageName, Constants.FILE_NAME_SEPARATOR);
                if (StringUtils.substringBeforeLast(imageFileNames[0], ".").length() != 36) {
                    continue;
                }
                productImage = new ProductImage();
                productImage.setProductId(product.getId());
                productImage.setNewName(imageFileNames[0]);
                if (imageFileNames.length == 2) {
                    productImage.setOldName(imageFileNames[1]);
                }
                productImage.setDisplayOrder(displayOrder);
                this.productImageMapper.insert(productImage);
                displayOrder++;
            }
        }

        if (StringUtils.isNotBlank(product.getVideoName())) {
            String[] videoFileNames = StringUtils.split(product.getVideoName(), Constants.FILE_NAME_SEPARATOR);
            if (StringUtils.substringBeforeLast(videoFileNames[0], ".").length() == 36) {
                ProductVideo productVideo = new ProductVideo();
                productVideo.setProductId(product.getId());
                productVideo.setDisplayOrder(1);
                productVideo.setName(videoFileNames[0]);
                this.productVideoMapper.insert(productVideo);
            }
        }
        return new ResponseResult(true, "产品信息保存成功");
    }

    /**
     * 
     */
    @Override
    public PaginationDto<ProductListDto> search(ProductSearchDto sdata) {
        if (StringUtils.isNotBlank(sdata.getOrderBy())) {
            sdata.setOrderBy("A." + sdata.getOrderBy());
        }
        PaginationDto<ProductListDto> result = new PaginationDto<ProductListDto>();
        int totalCount = this.productMapper.countAll(sdata);
        if (totalCount == 0) {
        	result.setCurrentPage(1);
        	result.setTotalPages(0);
        	return result;
        }
        // 先向上取整，再向下取整
        result.setTotalPages(new Double(Math.ceil((double)totalCount/(double)Constants.RECORD_COUNT_PER_PAGE)).intValue());
        sdata.setCurrentPage(sdata.getCurrentPage() <= result.getTotalPages() ? sdata.getCurrentPage() : result.getTotalPages());
        result.setCurrentPage(sdata.getCurrentPage());
        List<ProductListDto> list = this.productMapper.selectAll(sdata);
        result.setRecords(list);

        return result;
    }

    @Override
    public PaginationDto<ProductListDto> searchUnderShelf(ProductSearchDto sdata) {
        if (StringUtils.isNotBlank(sdata.getOrderBy())) {
            sdata.setOrderBy("A." + sdata.getOrderBy());
        }
        PaginationDto<ProductListDto> result = new PaginationDto<ProductListDto>();
        int totalCount = this.productMapper.countAllUnderShelf(sdata);
        if (totalCount == 0) {
        	result.setCurrentPage(1);
        	result.setTotalPages(0);
        	return result;
        }
        // 先向上取整，再向下取整
        result.setTotalPages(new Double(Math.ceil((double)totalCount/(double)Constants.RECORD_COUNT_PER_PAGE)).intValue());
        sdata.setCurrentPage(sdata.getCurrentPage() <= result.getTotalPages() ? sdata.getCurrentPage() : result.getTotalPages());
        result.setCurrentPage(sdata.getCurrentPage());
        List<ProductListDto> list = this.productMapper.selectAllUnderShelf(sdata);
        result.setRecords(list);
        
        return result;
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
            if (StringUtils.isBlank(image.getOldName())) {
                previewConfig.setCaption(image.getNewName());
            } else {
                previewConfig.setCaption(image.getOldName());
            }
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
        String[] imageNames = ImageUtils.removeDuplicate(product.getImageNames());
        if (ArrayUtils.isNotEmpty(imageNames)) {
            int displayOrder = 1;
            ProductImage productImage = null;
            String[] imageFileNames = null;
            for (String imageName : imageNames) {
                if (StringUtils.isBlank(imageName)) {
                    continue;
                }
                imageFileNames = StringUtils.split(imageName, Constants.FILE_NAME_SEPARATOR);
                if (StringUtils.substringBeforeLast(imageFileNames[0], ".").length() != 36) {
                    continue;
                }
                productImage = new ProductImage();
                productImage.setProductId(product.getId());
                productImage.setNewName(imageFileNames[0]);
                if (imageFileNames.length == 2) {
                    productImage.setOldName(imageFileNames[1]);
                }
                productImage.setDisplayOrder(displayOrder);
                this.productImageMapper.insert(productImage);
                displayOrder++;
            }
        }

        this.productVideoMapper.deleteByProductId(product.getId());
        if (StringUtils.isNotBlank(product.getVideoName())) {
            String[] videoFileNames = StringUtils.split(product.getVideoName(), Constants.FILE_NAME_SEPARATOR);
            if (StringUtils.substringBeforeLast(videoFileNames[0], ".").length() == 36) {
                ProductVideo productVideo = new ProductVideo();
                productVideo.setProductId(product.getId());
                productVideo.setDisplayOrder(1);
                productVideo.setName(videoFileNames[0]);
                this.productVideoMapper.insert(productVideo);
            }
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