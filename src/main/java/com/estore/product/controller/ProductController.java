/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estore.base.ResponseResult;
import com.estore.product.dto.ProductDto;
import com.estore.product.dto.ProductListDto;
import com.estore.product.dto.ProductPair;
import com.estore.product.dto.ProductSearchDto;
import com.estore.product.entity.Product;
import com.estore.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@RestController("productController")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 保存产品信息
     */
    @RequestMapping(path = "/product/add", method = RequestMethod.POST)
    public ResponseResult add(@Valid Product data, BindingResult binding) {
        if (binding.hasErrors()) {
            log.error("Data validation failed.", binding.getAllErrors());
            return new ResponseResult(false, "保存产品信息验证失败");
        }
        if (ArrayUtils.isEmpty(data.getImageNames())) {
            return new ResponseResult(false, "请上传至少一张产品图片。");
        }
        return this.productService.save(data);
    }

    /**
     * 
     * @param data
     * @param binding
     * @return
     */
    @RequestMapping(path = "/product/modify", method = RequestMethod.POST)
    public ResponseResult modify(@Valid Product data, BindingResult binding) {
        if (binding.hasErrors()) {
            log.error("Data validation failed.", binding.getAllErrors());
            return new ResponseResult(false, "保存产品信息验证失败");
        }
        if (ArrayUtils.isEmpty(data.getImageNames())) {
            return new ResponseResult(false, "请上传至少一张产品图片。");
        }
        this.productService.modify(data);
        return new ResponseResult(true, "产品信息保存成功");
    }

    /**
     * 
     * @param sdata
     * @return
     */
    @RequestMapping(path = "/product/search", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseResult search(ProductSearchDto sdata) {
        List<ProductListDto> results = this.productService.search(sdata);
        if (CollectionUtils.isEmpty(results)) {
            return new ResponseResult(false, "查询数据不存在");
        }
        return new ResponseResult(true, results);
    }

    @RequestMapping(path = "/product/searchUnderShelf", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseResult searchUnderShelf(ProductSearchDto sdata) {
        List<ProductListDto> results = this.productService.searchUnderShelf(sdata);
        if (CollectionUtils.isEmpty(results)) {
            return new ResponseResult(false, "查询数据不存在");
        }
        return new ResponseResult(true, results);
    }

    @RequestMapping(path = "/product/changeShelf", method = RequestMethod.POST)
    public ResponseResult changeShelf(@RequestParam(name = "productId", required = true) int productId) {
        boolean result = this.productService.changeShelf(productId);
        if (!result) {
            return new ResponseResult(false, "上架或下架失败");
        }
        return new ResponseResult(true, "上架或下架成功");
    }

    /**
     * 取得产品详细信息
     * 
     * @param productId
     * @return
     */
    
    @RequestMapping(path = "/product/getDetail")
    @CrossOrigin
    public ResponseResult getDetail(@RequestParam(name = "productId", required = true) int productId) {
        ProductDto result = this.productService.getDetail(productId);
        if (null == result) {
            return new ResponseResult(false, "查询数据不存在");
        }
        return new ResponseResult(true, result);
    }
 
    /**
     * 
     * @param productId
     * @return
     */
    @RequestMapping(path = "/product/remove", method = RequestMethod.POST)
    public ResponseResult remove(int productId) {
        this.productService.remove(productId);
        return new ResponseResult(true, "产品信息删除成功");
    }

    /* ------------------ 移动端数据处理相关方法 ------------------------------------------------------------*/
    /**
     * 
     * @param sdata
     * @return
     */
    @RequestMapping(path = "/app/product/search", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseResult searchForApp(ProductSearchDto sdata) {
        List<ProductPair> results = this.productService.searchForApp(sdata);
        if (CollectionUtils.isEmpty(results)) {
            return new ResponseResult(false, new ArrayList<ProductPair>());
        }
        return new ResponseResult(true, results);
    }
}