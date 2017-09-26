/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.utils;

import com.estore.user.dto.LoginAccount;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public final class UserUtils {
    private UserUtils() {
        throw new RuntimeException("UserUtils cannot be instantiated.");
    }

    private static final ThreadLocal<LoginAccount> CURRENT_LOGIN_ACCOUNT = new ThreadLocal<LoginAccount>();

    public static void setLoginAccount(LoginAccount account) {
        CURRENT_LOGIN_ACCOUNT.set(account);
    }

    /**
     * 
     * @return
     */
    public static boolean isAdmin() {
        LoginAccount account = CURRENT_LOGIN_ACCOUNT.get();
        if (null == account) {
            return false;
        }
        return Constants.ROLE_CODE_ADMIN.equalsIgnoreCase(account.getRoleCode());
    }

    /**
     * 
     * @return
     */
    public static boolean isUser() {
        LoginAccount account = CURRENT_LOGIN_ACCOUNT.get();
        if (null == account) {
            return false;
        }
        return Constants.ROLE_CODE_USER.equalsIgnoreCase(account.getRoleCode());
    }

    /**
     * 
     * @return
     */
    public static boolean isLevel1Agent() {
        LoginAccount account = CURRENT_LOGIN_ACCOUNT.get();
        if (null == account) {
            return false;
        }
        return Constants.ROLE_CODE_LEVEL1_AGENT.equalsIgnoreCase(account.getRoleCode());
    }

    /**
     * 
     * @return
     */
    public static boolean isLevel2Agent() {
        LoginAccount account = CURRENT_LOGIN_ACCOUNT.get();
        if (null == account) {
            return false;
        }
        return Constants.ROLE_CODE_LEVEL2_AGENT.equalsIgnoreCase(account.getRoleCode());
    }
}