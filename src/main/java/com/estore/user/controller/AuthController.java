/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.user.controller;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estore.base.ResponseResult;
import com.estore.user.dto.LoginAccount;
import com.estore.user.dto.UserPasswordDto;
import com.estore.user.service.UserService;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@RestController(value = "authController")
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * 
     * @param phoneNumber
     * @param password
     * @return
     */
    @RequestMapping(path = "/app/login", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseResult login(String phoneNumber, String password) {
        if (StringUtils.isBlank(phoneNumber)) {
            return new ResponseResult(false, "用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return new ResponseResult(false, "密码不能为空");
        }

        LoginAccount account = userService.authenticate(phoneNumber, password);
        if (account != null) {
            return new ResponseResult(true, account);
        }
        return new ResponseResult(false, "用户名或密码不正确");
    }

    @RequestMapping(path = "/app/checkAuthToken", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseResult checkAuthToken(String authToken) {
        if (StringUtils.isBlank(authToken)) {
            return new ResponseResult(false, "认证失败");
        }

        boolean result = userService.checkAuthToken(authToken);
        if (result) {
            return new ResponseResult(true, "认证成功");
        }
        return new ResponseResult(false, "用户名或密码不正确");
    }

    @RequestMapping(path = "/app/logout", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseResult logout(String authToken) {
        if (StringUtils.isBlank(authToken)) {
            return new ResponseResult(false, "退出失败");
        }

        boolean result = userService.logout(authToken);
        if (result) {
            return new ResponseResult(true, "退出成功");
        }
        return new ResponseResult(false, "退出失败");
    }

    @RequestMapping(path = "/app/modifyPassword", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseResult modifyPassword(@Valid @RequestBody UserPasswordDto data) {
        return this.userService.modifyPassword(data);
    }
}