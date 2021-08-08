package com.icommerce.catalog;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;



@EnableFeignClients(clients = FeignAPI.class)
@SpringBootApplication
public class CatalogServiceTestsApp {
   /* @Bean
    @Primary
    public CachingConnectionFactory rabbitAdmin() {
        return Mockito.mock(CachingConnectionFactory.class);
    }*/
	@Bean
	public AmqpAdmin amqpAdmin(final ConnectionFactory connectionFactory) {
	    RabbitAdmin admin = new RabbitAdmin(connectionFactory);
	    return admin;
	}
	
    @Bean
    ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory();
    }
    
    
    @RequestMapping(value = "inventory")
    public String testFeign() {
       return "1";
    }
}