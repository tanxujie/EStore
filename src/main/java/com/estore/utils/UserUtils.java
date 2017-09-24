/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.utils;

import org.apache.shiro.SecurityUtils;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public final class UserUtils {
    private UserUtils() {
        throw new RuntimeException("UserUtils cannot be instantiated.");
    }

    /**
     * 
     * @return
     */
    public static boolean isAdmin() {
        return SecurityUtils.getSubject().hasRole(Constants.ROLE_CODE_ADMIN);
    }

    /**
     * 
     * @return
     */
    public static boolean isUser() {
        return SecurityUtils.getSubject().hasRole(Constants.ROLE_CODE_USER);
    }

    /**
     * 
     * @return
     */
    public static boolean isLevel1Agent() {
        return SecurityUtils.getSubject().hasRole(Constants.ROLE_CODE_LEVEL1_AGENT);
    }

    /**
     * 
     * @return
     */
    public static boolean isLevel2Agent() {
        return SecurityUtils.getSubject().hasRole(Constants.ROLE_CODE_LEVEL2_AGENT);
    }
}