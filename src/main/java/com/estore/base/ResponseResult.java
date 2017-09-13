/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.base;

import java.io.Serializable;

import lombok.Data;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Data
public class ResponseResult implements Serializable {
    private static final long serialVersionUID = 4362546489737574138L;

    private boolean success;
    private Object data;

    /**
     * 
     */
    public ResponseResult(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }
}