/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.user.dto;

import com.estore.base.AbstractBaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserPasswordDto extends AbstractBaseDto {
    private static final long serialVersionUID = -2879221787715237492L;

    private int userId;

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 新确认密码
     */
    private String newConfirmPassword;
}
