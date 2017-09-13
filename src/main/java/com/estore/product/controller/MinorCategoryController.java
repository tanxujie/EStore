/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.base.ResponseResult;
import com.estore.product.entity.MinorCategory;
import com.estore.product.service.MinorCategoryService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@RestController("minorCategoryController")
@RequestMapping(path = "/minorcategory")
@Slf4j
public class MinorCategoryController {

    @Autowired
    private MinorCategoryService minorCategoryService;

    /**
     * 
     * @param data
     * @param result
     * @return
     */
    @RequestMapping(path = "/save")
    public ResponseResult save(@Valid MinorCategory data, BindingResult result) {
        if (result.hasErrors()) {
            log.error("小分类消息验证失败");
            return new ResponseResult(false, "小分类信息验证失败");
        }
        this.minorCategoryService.save(data);
        return new ResponseResult(true, "小分类信息保存成功");
    }

    /**
     * 
     * @param ids
     * @return
     */
    @RequestMapping(path = "/remove")
    public ResponseResult remove(int[] ids) {
        this.minorCategoryService.remove(ids);
        return new ResponseResult(true, "小分类信息删除成功");
    }

    /**
     * 
     * @param data
     * @param result
     * @return
     */
    @RequestMapping(path = "/modify")
    public ResponseResult modify(@Valid MinorCategory data, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseResult(false, "小分类信息验证失败");
        }
        this.minorCategoryService.modify(data);
        return new ResponseResult(true, "小分类信息保存成功");
    }

    /**
     * 
     * @param condition
     * @return
     */
    @RequestMapping(path = "/search")
    public ResponseResult search(String condition) {
        List<MinorCategory> results = this.minorCategoryService.search(condition);
        if (CollectionUtils.isEmpty(results)) {
            return new ResponseResult(false, new ArrayList<MinorCategory>());
        }
        return new ResponseResult(true, results);
    }

    /**
     * 
     * @param id
     * @return
     */
    @RequestMapping(path = "/getdetail")
    public ResponseResult getDetail(int id) {
        MinorCategory result = this.minorCategoryService.getDetail(id);
        if (null == result) {
            return new ResponseResult(false, "查询信息不存在");
        }
        return new ResponseResult(true, result);
    }
}