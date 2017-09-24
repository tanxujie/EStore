/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.microclass.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estore.base.ResponseResult;
import com.estore.microclass.entity.MicroClassVideo;
import com.estore.microclass.service.MicroClassVideoService;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@RestController("microClassVideoController")
@Slf4j
public class MicroClassVideoController {

    @Autowired
    private MicroClassVideoService microClassVideoService;

    /**
     * 
     * @param data
     * @param binding
     * @return
     */
    @RequestMapping(path = "/microclassvideo/save", method = RequestMethod.POST)
    public ResponseResult save(@Valid MicroClassVideo data, BindingResult binding) {
        if (binding.hasErrors()) {
            log.error("Data validation failed.", binding.getAllErrors());
            return new ResponseResult(false, "微课堂视频数据验证失败");
        }
        this.microClassVideoService.save(data);
        return new ResponseResult(true, "微课堂数据保存成功");
    }

    /**
     * 
     * @param ids
     * @return
     */
    @RequestMapping(path = "/microclassvideo/remove", method = RequestMethod.POST)
    public ResponseResult remove(int id) {
        this.microClassVideoService.remove(id);
        return new ResponseResult(true, "微课堂数据删除成功");
    }

    /**
     * 
     * @param data
     * @param binding
     * @return
     */
    @RequestMapping(path = "/microclassvideo/modify", method = RequestMethod.POST)
    public ResponseResult modify(@Valid MicroClassVideo data, BindingResult binding) {
        if (binding.hasErrors()) {
            log.error("Data validation failed.", binding.getAllErrors());
            return new ResponseResult(false, "微课堂视频数据验证失败");
        }
        this.microClassVideoService.modify(data);
        return new ResponseResult(true, "微课堂数据更新成功");
    }

    /**
     * 
     * @param microClassId
     * @return
     */
    @RequestMapping(path = "/microclassvideo/searchByMicroClassId", method = RequestMethod.GET)
    public ResponseResult searchByMicroClassId(int id) {
        List<MicroClassVideo> results = this.microClassVideoService.searchByMicroClassId(id);
        if (CollectionUtils.isEmpty(results)) {
            return new ResponseResult(false, new ArrayList<MicroClassVideo>(0));
        }
        return new ResponseResult(true, results);
    }

    /**
     * 
     * @return
     */
    @RequestMapping(path = "/microclassvideo/search", method = RequestMethod.GET)
    public ResponseResult search(@RequestParam(required = false, name = "condition") String condition) {
        List<MicroClassVideo> results = this.microClassVideoService.search(condition);
        if (CollectionUtils.isEmpty(results)) {
            return new ResponseResult(false, new ArrayList<MicroClassVideo>(0));
        }
        return new ResponseResult(true, results);
    }

    /**
     * 
     * @param id
     * @return
     */
    @RequestMapping(path = "/microclassvideo/getDetail", method = RequestMethod.GET)
    public ResponseResult getDetail(int id) {
        MicroClassVideo result = this.microClassVideoService.getDetail(id);
        if (null == result) {
            return new ResponseResult(false, "查询数据不存在");
        }
        return new ResponseResult(true, result);
    }
}