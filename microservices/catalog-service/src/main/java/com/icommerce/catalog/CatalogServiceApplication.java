package com.icommerce.catalog;

import java.util.function.Consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

import com.icommerce.catalog.dto.MessageDto;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class CatalogServiceApplication { 

	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceApplication.class, args);
	}
	

}
