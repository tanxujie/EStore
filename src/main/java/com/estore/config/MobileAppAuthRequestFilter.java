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
    private static final String LOGIN_URL = "/app/login";
    private static final String LOGOUT_URL = "/app/logout";

    @Autowired
    private EhCacheCacheManager cacheManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (!StringUtils.contains(uri, "/app/")) {
            filterChain.doFilter(request, response);
        } else if (StringUtils.endsWith(uri, LOGIN_URL) 
                || StringUtils.endsWith(uri, LOGOUT_URL)) {
            filterChain.doFilter(request, response);
        } else {
            String token = request.getParameter(AUTHENTICATED_USER_TOKEN);
            if (StringUtils.isBlank(uri)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            if (this.validateAuthToken(token)) {
                filterChain.doFilter(request, response);;
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
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