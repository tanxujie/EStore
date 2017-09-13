/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.microclass.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.base.ResponseResult;
import com.estore.microclass.entity.MicroClass;
import com.estore.microclass.service.MicroClassService;

/**
 *
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@RestController("microClassController")
public class MicroClassController {

    @Autowired
    private MicroClassService microClassService;

    /**
     * 
     * @param data
     * @param binding
     * @return
     */
    @RequestMapping(path = "/microclass/save")
    public ResponseResult save(@Valid MicroClass data, BindingResult binding) {
        if (binding.hasErrors()) {
            return new ResponseResult(false, "保存微课堂数据验证错误");
        }
        this.microClassService.save(data);
        return new ResponseResult(true, "微课堂数据保存成功");
    }

    /**
     * 
     * @param ids
     * @return
     */
    @RequestMapping(path = "/microclass/remove")
    public ResponseResult remove(int[] ids) {
        this.microClassService.remove(ids);
        return new ResponseResult(true, "微课堂数据删除成功");
    }

    /**
     * 
     * @param data
     * @param binding
     * @return
     */
    @RequestMapping(path = "/microclass/modify")
    public ResponseResult modify(@Valid MicroClass data, BindingResult binding) {
        if (binding.hasErrors()) {
            return new ResponseResult(false, "保存微课堂数据验证错误");
        }
        this.microClassService.modify(data);
        return new ResponseResult(true, "微课堂数据更新成功");
    }

    /**
     * 
     * @param condition
     * @return
     */
    @RequestMapping(path = "/microclass/search")
    public ResponseResult search(String condition) {
        List<MicroClass> results = this.microClassService.search(condition);
        if (CollectionUtils.isEmpty(results)) {
            return new ResponseResult(false, new ArrayList<>());
        }
        return new ResponseResult(true, results);
    }

    @RequestMapping(path = "/microclass/edit")
    public MicroClass getDetail() {
        return new MicroClass();
    }

    /**
     * 
     * @param id
     * @return
     */
    @RequestMapping(path = "/microclass/getDetail")
    public ResponseResult getDetail(int id) {
        MicroClass result = this.microClassService.getDetail(id);
        if (null == result) {
            return new ResponseResult(false, "查询数据不存在");
        }
        return new ResponseResult(true, result);
    }

    /*------------------手机端数据查询方法--------------------------------------*/
    /**
     * 
     * @param condition
     * @return
     */
    @RequestMapping(path = "/app/microclass/search")
    @CrossOrigin
    public ResponseResult searchForApp() {
        List<MicroClass> results = this.microClassService.searchForApp();
        if (CollectionUtils.isEmpty(results)) {
            return new ResponseResult(false, new ArrayList<>(0));
        }
        return new ResponseResult(true, results);
    }
}