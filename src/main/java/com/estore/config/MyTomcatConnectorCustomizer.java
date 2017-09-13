/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
    @Override
    public void customize(Connector connector) {
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();  
        //设置最大连接数  
        protocol.setMaxConnections(2000);  
        //设置最大线程数  
        protocol.setMaxThreads(2000);  
        protocol.setConnectionTimeout(100000);  
    }
}