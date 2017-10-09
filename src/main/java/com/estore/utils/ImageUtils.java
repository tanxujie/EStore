/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
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

    public static String[] removeDuplicate(String[] fileNames) {
        if (ArrayUtils.isEmpty(fileNames)) {
            return fileNames;
        }

        Map<String, Object> cache = new HashMap<>();
        List<String> rtFiles = new ArrayList<>();
        for (String fileName : fileNames) {
            if (cache.containsKey(fileName)) {
                continue;
            }
            rtFiles.add(fileName);
            cache.put(fileName, null);
        }
        return rtFiles.toArray(new String[0]);
    }
}
