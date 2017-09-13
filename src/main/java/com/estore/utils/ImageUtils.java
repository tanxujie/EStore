/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * [Title]: 
 * [Description]: 
 *
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 * @version 1.0.1
 * @since   1.0.1
 * @history 2017-08-09 Created by Tan XuJie
 */
public final class ImageUtils {
    private ImageUtils() {
        throw new RuntimeException();
    }

    private static String[] VALID_IMAGE_SUFFIXS = {"jpg", "jpeg", "png", "bmp", "tif", "tiff", "gif"};

    /**
     * 
     * @param fileName
     * @return
     */
    public static boolean isValidImage(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return false;
        }

        String suffix = StringUtils.substringAfterLast(fileName, ".");
        if (StringUtils.isBlank(suffix)) {
            return false;
        }

        suffix = suffix.toLowerCase();
        for (String s : VALID_IMAGE_SUFFIXS) {
            if (s.equals(suffix)) {
                return true;
            }
        }
        return false;
    }
}
