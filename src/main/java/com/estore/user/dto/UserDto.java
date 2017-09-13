/**
 * Copyright(C) 2017 the original author Tan XuJie.
 * All rights reserved.
 */
package com.estore.user.dto;

import com.estore.base.AbstractBaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Tan XuJie
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto extends AbstractBaseDto {
    private static final long serialVersionUID = -2152025532601900914L;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private int age;

    /**
     * 地址
     */
    private String address;

    /**
     * 是否有效
     */
    private boolean enabled;

    /**
     * 新账户确认者
     */
    private int enabledBy;

    /**
     * 角色编码
     */
    private String roleCode;
}
