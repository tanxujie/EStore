/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.base.ResponseResult;
import com.estore.product.entity.MajorCategory;
import com.estore.product.service.MajorCategoryService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@RestController("majorCategoryController")
@RequestMapping(path = "/majorcategory")
@Slf4j
public class MajorCategoryController {

    @Autowired
    private MajorCategoryService majorCategoryService;

    @RequestMapping(path = "/getAllOptions")
    public ResponseResult getAllOptions() {
        return new ResponseResult(true, this.majorCategoryService.getAllOptions());
    }

    /**
     * 
     * @param data
     * @param binding
     * @return
     */
    @RequestMapping(path = "/save")
    public ResponseResult save(@Valid MajorCategory data, BindingResult binding) {
        if (binding.hasErrors()) {
            log.error("保存大分类数据验证失败");
            return new ResponseResult(false, "保存数据验证失败");
        }
        this.majorCategoryService.save(data);
        return new ResponseResult(true, "大分类信息保存成功");
    }

    /**
     * 
     * @param ids
     * @return
     */
    @RequestMapping(path = "/remove")
    public ResponseResult remove(int[] ids) {
        this.majorCategoryService.remove(ids);
        return new ResponseResult(true, "大分类信息删除成功");
    }

    /**
     * 
     * @param data
     * @return
     */
    @RequestMapping(path = "/modify")
    public ResponseResult modify(MajorCategory data) {
        this.majorCategoryService.modify(data);
        return new ResponseResult(true, "大分类信息更新成功");
    }

    /**
     * 
     * @param condition
     * @return
     */
    @RequestMapping(path = "/search")
    public ResponseResult search(String condition) {
        List<MajorCategory> results = this.majorCategoryService.search(condition);
        if (CollectionUtils.isEmpty(results)) {
            return new ResponseResult(false, new ArrayList<MajorCategory>());
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
        MajorCategory result = this.majorCategoryService.getDetail(id);
        if (null == result) {
            return new ResponseResult(false, "该数据不存在");
        }
        return new ResponseResult(true, result);
    }
}