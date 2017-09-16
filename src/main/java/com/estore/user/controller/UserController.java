/**
 * Copyright(C) 2017 the original author Tan XuJie.
 * All rights reserved.
 */
package com.estore.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estore.base.ResponseResult;
import com.estore.user.dto.LowerAgent;
import com.estore.user.dto.UserPasswordDto;
import com.estore.user.entity.User;
import com.estore.user.service.UserService;

/**
 * @author Tan XuJie
 *
 */
@RestController(value = "userController")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 
     * @param data
     * @param binding
     * @return
     */
    @RequestMapping(path = "/user/save")
    public ResponseResult save(@Valid User data, BindingResult binding) {
        if (binding.hasErrors()) {
            return new ResponseResult(false, "用户信息验证错误");
        }
        this.userService.save(data);
        return new ResponseResult(true, "用户保存成功");
    }

    /**
     * 
     * @param sdata
     * @param binding
     * @return
     */
    @RequestMapping(path = "/user/search")
    public ResponseResult search(@Valid User sdata, BindingResult binding) {
        List<User> results = this.userService.search(sdata);
        if (CollectionUtils.isEmpty(results)) {
            return new ResponseResult(false, "查询数据不存在");
        }
        return new ResponseResult(true, results);
    }

    /**
     * 
     * @param ids
     * @return
     */
    @RequestMapping(path = "/user/remove")
    public ResponseResult remove(int[] ids) {
        this.userService.remove(ids);
        return new ResponseResult(true, "用户删除成功");
    }

    /**
     * 
     * @param data
     * @param binding
     * @return
     */
    @RequestMapping(path = "/user/modify")
    public ResponseResult modify(@Valid User data, BindingResult binding) {
        if (binding.hasErrors()) {
            return new ResponseResult(false, "用户数据验证错误");
        }
        this.userService.modify(data);
        return new ResponseResult(true, "用户数据更新成功");
    }

    /**
     * 
     * @param data
     * @return
     */
    @RequestMapping(path = "/user/modifyPassword")
    public ResponseResult modifyPassword(@Valid UserPasswordDto data, BindingResult binding) {
        if (binding.hasErrors()) {
            return new ResponseResult(false, "用户信息验证失败");
        }
        this.userService.modifyPassword(data);
        return new ResponseResult(false, "密码更新成功");
    }

    /**
     * 
     * @param phoneNumber
     * @return
     */
    @RequestMapping(path = "/user/findByPhoneNumber")
    public ResponseResult findByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber) {
        User result = this.userService.findByPhoneNumber(phoneNumber);
        if (null == result) {
            return new ResponseResult(false, "查询数据不存在");
        }
        return new ResponseResult(true, result);
    }

    /* ------------------ 移动端数据处理相关方法 ------------------------------------------------------------*/
    @RequestMapping(path = "/app/user/searchLowerAgents", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseResult searchLowerAgents(
            @RequestParam("supperAgentId") int supperAgentId, 
            @RequestParam("condition") String condition) {
        List<LowerAgent> agents = this.userService.searchLowerAgents(supperAgentId, condition);
        if (CollectionUtils.isEmpty(agents)) {
            return new ResponseResult(false, new ArrayList<LowerAgent>(0));
        }
        return new ResponseResult(true, agents);
    }

    @RequestMapping(path = "/app/user/getLowerAgentCount", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseResult getLowerAgentsCount(@RequestParam("supperAgentId") int supperAgentId) {
        return new ResponseResult(true, this.userService.getLowerAgentsCount(supperAgentId));
    }

    @RequestMapping(path = "/app/user/saveLowerAgent", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseResult saveLowerAgent(@Valid @RequestBody LowerAgent agent) {
        this.userService.save(agent);
        return new ResponseResult(true, "保存成功");
    }
}