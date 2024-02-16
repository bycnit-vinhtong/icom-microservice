package com.icommerce.catalog.event;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.aspectj.bridge.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.icommerce.catalog.dto.EventCatalog;
import com.icommerce.catalog.dto.MessageDto;
import com.icommerce.catalog.dto.SearchCriteria;
import com.icommerce.catalog.service.ProductServiceImpl;


@Service
public class LogAuditProducer {
  public static final String DESTINATION = "catalog-audit-out-0";

  @Autowired 
  private StreamBridge streamBridge;

  private static final Logger logger = LoggerFactory.getLogger(LogAuditProducer.class);


  public void logAudit(SearchCriteria criteria, Long productId, EventCatalog.Type event) {
    streamBridge.send(DESTINATION, new EventCatalog(event, productId, criteria));
  }

  public void sendMessage(){
    boolean sent = streamBridge.send("producer-out-0",new MessageDto(" jack from Stream bridge"));
    if (sent) {
      logger.info("Message sent with success !");
    } else {
      logger.warn("Could not receive send message !");
    }
  }


  //Using for batch processing, by default, it will send 1 messages in 1 second
  /*@Bean
  public Supplier<MessageDto> producer() {
      return () -> new MessageDto(" jack from Streams");
  }*/

}