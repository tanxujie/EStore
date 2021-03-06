/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.base;

import java.io.Serializable;
import java.time.ZonedDateTime;

import lombok.Data;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Data
public abstract class AbstractBaseDto implements Serializable {
    private static final long serialVersionUID = 614971075592233285L;

    /**
     * ID
     */
    private int id;

    /**
     * 创建者
     */
    private int createdBy;

    /**
     * 创建时间
     */
    private ZonedDateTime createdTime;

    /**
     * 更新者
     */
    private int updatedBy;

    /**
     * 更新时间
     */
    private ZonedDateTime updatedTime;

    /**
     * 数据版本
     */
    private int version;
}