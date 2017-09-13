/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.user.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Data
public class LoginAccount implements Serializable {
    private static final long serialVersionUID = 8796217032097414570L;

    /**
     * 用户ID
     */
    private int id;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 姓名
     */
    private String name;
    /**
     * 昵称
     */
    private String aliasName;
    /**
     * 微信号
     */
    private String wechatNumber;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 银行卡
     */
    private String bankCard;
    /**
     * 登录识别Token
     */
    private String authToken;
}