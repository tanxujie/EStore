/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.config;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceRegionHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 *  extends WebMvcConfigurationSupport
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public CookieLocaleResolver cookieLocaleResolver(){
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.CHINA);
        return cookieLocaleResolver;
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        //builder.propertyNamingStrategy(PropertyNamingStrategy.);
        builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return builder;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);

        return registrationBean;
    }

    @Bean(name="mobileAppAuthDelegatingFilterProxy")
    public FilterRegistrationBean mobileAppAuthDelegatingFilterProxy() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy httpBasicFilter = new DelegatingFilterProxy();
        registrationBean.setFilter(httpBasicFilter);
        //registrationBean.setFilter(new MobileAppAuthRequestFilter());
        //registrationBean.addUrlPatterns("/app/*");
        Map<String,String> m = new HashMap<String,String>();
        m.put("targetBeanName","mobileAppAuthRequestFilter");
        m.put("targetFilterLifecycle","true");
        registrationBean.setInitParameters(m);
        registrationBean.addUrlPatterns("/app/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean(name="mobileAppAuthRequestFilter")
    public MobileAppAuthRequestFilter mobileAppAuthRequestFilter() {
        return new MobileAppAuthRequestFilter();
    }

//    @Bean
//    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
//        return new ByteArrayHttpMessageConverter();
//    }

//    @Bean
//    public StringHttpMessageConverter stringHttpMessageConverter() {
//        StringHttpMessageConverter shmc = new StringHttpMessageConverter();
//        shmc.setWriteAcceptCharset(false);
//        return shmc;
//    }
//
//    @Bean
//    public ResourceHttpMessageConverter resourceHttpMessageConverter() {
//        return new ResourceHttpMessageConverter();
//    }
//
//    @Bean
//    public ResourceRegionHttpMessageConverter resourceRegionHttpMessageConverter() {
//        return new ResourceRegionHttpMessageConverter();
//    }
//
//    @Bean
//    public SourceHttpMessageConverter sourceHttpMessageConverter() {
//        return new SourceHttpMessageConverter();
//    }
//
//    @Bean
//    public AllEncompassingFormHttpMessageConverter allEncompassingFormHttpMessageConverter() {
//        return new AllEncompassingFormHttpMessageConverter();
//    }
//
//    @Bean
//    public Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter() {
//        return new Jaxb2RootElementHttpMessageConverter();
//    }
//
//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        return new MappingJackson2HttpMessageConverter();
//    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new ResourceRegionHttpMessageConverter());
    }

//    @Override
//    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new ByteArrayHttpMessageConverter());
//        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
//        stringConverter.setWriteAcceptCharset(false);
//        converters.add(stringConverter);
//        converters.add(new ResourceHttpMessageConverter());
//        converters.add(new ResourceRegionHttpMessageConverter());
//        converters.add(new SourceHttpMessageConverter());
//        converters.add(new AllEncompassingFormHttpMessageConverter());
//        converters.add(new Jaxb2RootElementHttpMessageConverter());
//        converters.add(new MappingJackson2HttpMessageConverter());
//        //super.addDefaultHttpMessageConverters(new ArrayList<HttpMessageConverter<?>>());
//    }
//
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/resources/static/");
//        //super.addResourceHandlers(registry);
//    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter(){
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**");
//            }
//        };
//    }
}