/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.user.dto;

import lombok.Data;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Data
public class UserSearchDto {

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 姓名
     */
    private String name;

    /**
     * 地址
     */
    private String address;
}
