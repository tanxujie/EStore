/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.microclass.entity;

import java.util.List;

import com.estore.base.AbstractBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MicroClass extends AbstractBaseEntity {
    private static final long serialVersionUID = 1079522048427034899L;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 微课堂视频信息一览
     */
    private List<MicroClassVideo> microClassVideos;
}