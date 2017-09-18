/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Configuration
public class WebServerConfig {

    @Bean("embeddedServletContainerFactory")
    public EmbeddedServletContainerFactory embeddedServletContainerFactory()  
    {  
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();  
        tomcatFactory.setPort(80);
        //tomcatFactory.setPort(8080);
        tomcatFactory.addConnectorCustomizers(new MyTomcatConnectorCustomizer());  
        return tomcatFactory;  
    }
}