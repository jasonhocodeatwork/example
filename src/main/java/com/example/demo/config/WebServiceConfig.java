package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

@Configuration
public class WebServiceConfig {

    @Bean
    public SimpleXsdSchema usersSchema() {
        return new SimpleXsdSchema(
                new org.springframework.core.io.ClassPathResource("xsd/users.xsd"));
    }

    @Bean
    public SimpleXsdSchema ordersSchema() {
        return new SimpleXsdSchema(
                new org.springframework.core.io.ClassPathResource("xsd/orders.xsd"));
    }

    @Bean(name = "usersService")
    public DefaultWsdl11Definition defaultWsdl11Definition(SimpleXsdSchema usersSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UsersPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://example.com/soap/users");
        wsdl11Definition.setSchema(usersSchema);
        return wsdl11Definition;
    }

    @Bean(name = "ordersService")
    public DefaultWsdl11Definition defaultWsdl111Definition(SimpleXsdSchema ordersSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("OrdersPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://example.com/soap/orders");
        wsdl11Definition.setSchema(ordersSchema);
        return wsdl11Definition;
    }
}