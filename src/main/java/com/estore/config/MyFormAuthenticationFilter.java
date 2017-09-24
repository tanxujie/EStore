/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.config;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    private String loginFailureUrl;

    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
            ServletRequest request, ServletResponse response) {
        try {
            WebUtils.redirectToSavedRequest(request, response, this.loginFailureUrl);
        } catch (IOException ex) {
            // 
        }
        return false;
    }

    /**
     * @param loginFailureUrl the loginFailureUrl to set
     */
    public void setLoginFailureUrl(String loginFailureUrl) {
        this.loginFailureUrl = loginFailureUrl;
    }
}
