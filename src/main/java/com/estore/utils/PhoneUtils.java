/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public final class PhoneUtils {
    private PhoneUtils() {
        throw new RuntimeException();
    }

    private static final int MOBILE_PHONE_NO_LENGTH = 11;
    /**
     * 正则表达式：验证手机号
     */
    private static final String REGEX_MOBILE_PHONE_NO = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 
     * @param phoneNumber
     * @return
     */
    public static boolean isValid(String phoneNumber) {
        if (StringUtils.length(phoneNumber) != MOBILE_PHONE_NO_LENGTH) {
            return false;
        }
        return Pattern.matches(REGEX_MOBILE_PHONE_NO, phoneNumber);
    }

    /**
     * 
     * @param phoneNumber
     * @return
     */
    public static boolean isNotValid(String phoneNumber) {
        return !isValid(phoneNumber);
    }
}
