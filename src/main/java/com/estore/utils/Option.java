/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.utils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Data
@AllArgsConstructor
public class Option implements Serializable {

    private static final long serialVersionUID = -7814122015257937418L;

    private Object value;

    private Object text;
}