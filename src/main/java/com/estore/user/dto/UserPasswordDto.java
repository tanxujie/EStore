/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.user.dto;

import javax.validation.constraints.NotNull;

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

    /**
     * 旧密码
     */
    @NotNull(message = "旧密码不能为空")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotNull(message = "新密码不能为空")
    private String newPassword;

    /**
     * 新确认密码
     */
    @NotNull(message = "新确认密码不能为空")
    private String newConfirmPassword;
}
