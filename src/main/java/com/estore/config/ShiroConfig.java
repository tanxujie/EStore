/**
 * Copyright(C) 2017 the original author Tan XuJie.
 * All rights reserved.
 */
package com.estore.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author Tan XuJie
 *
 */
@Configuration
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager securityManager(
            CacheManager cacheShiroManager/*, SessionManager sessionManager*/) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.shiroDbRealm());
        securityManager.setCacheManager(cacheShiroManager);
        securityManager.setRememberMeManager(null);
//        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * 缓存管理器 使用Ehcache实现
     */
    @Bean
    public CacheManager cacheShiroManager(EhCacheManagerFactoryBean ehcache) {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(ehcache.getObject());
        return ehCacheManager;
    }

    @Bean("shiroRealm")
    public ShiroRealm shiroDbRealm() {
        return new ShiroRealm();
    }

//    /**
//     * rememberMe管理器, cipherKey生成见{@code Base64Test.java}
//     */
//    @Bean
//    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
//        CookieRememberMeManager manager = new CookieRememberMeManager();
//        manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA=="));
//        manager.setCookie(rememberMeCookie);
//        return manager;
//    }
//
//    /**
//     * 记住密码Cookie
//     */
//    @Bean
//    public SimpleCookie rememberMeCookie() {
//        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//        simpleCookie.setHttpOnly(true);
//        simpleCookie.setMaxAge(7 * 24 * 60 * 60);//7天
//        return simpleCookie;
//    }

//    @Bean
//    public DefaultWebSessionManager sessionManager(CacheManager cacheShiroManager) {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setCacheManager(cacheShiroManager);
//        sessionManager.setSessionIdCookieEnabled(false);
//        return sessionManager;
//    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login.do");
        shiroFilter.setSuccessUrl("/index.html");
        shiroFilter.setUnauthorizedUrl("/unauthorized.html");
        Map<String, Filter> filters = new LinkedHashMap<>();
        MyFormAuthenticationFilter loginFilter = new MyFormAuthenticationFilter();
        loginFilter.setLoginFailureUrl("/unauthenticated.html");
        filters.put("mylogin", loginFilter);

        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/login.html");
        filters.put("mylogout", logoutFilter);
        shiroFilter.setFilters(filters);

        // filter settings
        Map<String, String> filterChainDefinitionMap= new LinkedHashMap<>();
        filterChainDefinitionMap.put("/app/**","anon"); // DO NOT check request from mobile application
        filterChainDefinitionMap.put("/privacypolicy.html", "anon");
        filterChainDefinitionMap.put("/initLogin", "anon");
        filterChainDefinitionMap.put("/techsupport.html", "anon");
        filterChainDefinitionMap.put("/login.html", "anon");
        filterChainDefinitionMap.put("unauthenticated.html", "anon");
        filterChainDefinitionMap.put("unauthorized.html", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/download/**", "anon");
        filterChainDefinitionMap.put("/logout", "mylogout");
        filterChainDefinitionMap.put("/error.html","anon");
        filterChainDefinitionMap.put("/login.do", "authc");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }

//    /**
//     * 在方法中 注入 securityManager,进行代理控制
//     */
//    @Bean
//    @DependsOn(value = "securityManager")
//    public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager) {
//        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
//        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
//        bean.setArguments(new Object[]{securityManager});
//        return bean;
//    }
//
//    /**
//     * 保证实现了Shiro内部lifecycle函数的bean执行
//     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

//    /**
//     * 启用shrio授权注解拦截方式，AOP式方法级权限检查
//     */
//    @Bean
//    @DependsOn(value = "lifecycleBeanPostProcessor")
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        return new DefaultAdvisorAutoProxyCreator();
//    }
//
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
//                new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
}
