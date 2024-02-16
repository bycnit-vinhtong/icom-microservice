package com.icommerce.catalog.event;


import com.icommerce.catalog.dto.MessageDto;

import lombok.extern.log4j.Log4j2;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Log4j2
@Component
public class MessageProcessor {

    @Bean
	public Consumer<MessageDto> consumer() {
		return message -> {
            log.info("Handling CREATE message with id {}", message.getMessage());
        };
	}

    /*@KafkaListener(id = "my-client-application", topics = "first-topic")
    public void consumer(ConsumerRecord<String, MessageDto> consumerRecord) {
        System.out.println("Consumed Record Details: " + consumerRecord);
        MessageDto message = consumerRecord.value();
        System.out.println("Consumed Message" + message);
    }*/

}
