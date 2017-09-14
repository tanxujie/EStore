/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.microclass.entity;

import java.util.Date;

import com.estore.base.AbstractBaseEntity;
import com.estore.utils.Constants;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MicroClassVideo extends AbstractBaseEntity {
    private static final long serialVersionUID = -4233773005101034566L;

    private String authToken;
    /**
     * 微课堂ID
     */
    private int microClassId;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 视频原名称
     */
    private String oldName;

    /**
     * 视频新名称
     */
    private String newName;

    /**
     * 视频日期
     */
    private Date videoDate;

    /**
     * 返回微课堂视频Src地址
     * 
     * @return
     */
    public String getVideoSrc() {
        return Constants.WEB_SERVICE_ROOT_URL + "/download/video/" + this.newName + "?authToken=" + this.authToken;
    }
}
