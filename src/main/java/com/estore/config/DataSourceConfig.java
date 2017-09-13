/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 *
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Configuration
public class DataSourceConfig {

    /**
     * 
     * @param driverClassName
     * @param url
     * @param username
     * @param password
     * @return
     */
    @Bean
    public DataSource dataSource(@Value("${spring.datasource.driver-class-name}") String driverClassName, 
                                 @Value("${spring.datasource.url}") String url, 
                                 @Value("${spring.datasource.username}") String username,
                                 @Value("${spring.datasource.password}") String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        try {
            dataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}