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
public class LowerAgent implements Serializable {
    private static final long serialVersionUID = 4107648228877953311L;
    /**
     * ID
     */
    private int id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 微信号码
     */
    private String wechatNumber;
}