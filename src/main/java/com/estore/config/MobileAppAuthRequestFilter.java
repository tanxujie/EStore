/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.config;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.filter.OncePerRequestFilter;

import com.estore.utils.Constants;

/**
 * 
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 * @version 1.0.1
 */
public class MobileAppAuthRequestFilter extends OncePerRequestFilter {
    private static final String AUTHENTICATED_USER_TOKEN = "authToken";

    @Autowired
    private EhCacheCacheManager cacheManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authToken = request.getParameter(AUTHENTICATED_USER_TOKEN);
        if (StringUtils.isNotBlank(authToken)) {
            if (this.validateAuthToken(authToken)) {
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean validateAuthToken(String authToken) {
        ValueWrapper vw = this.cacheManager.getCache(Constants.GLOBAL_AUTH_TOKENS_CACHE).get(authToken);
        if (vw != null && ((LocalDate)vw.get()).isAfter(LocalDate.now())) {
            return true;
        }
        return false;
    }
}