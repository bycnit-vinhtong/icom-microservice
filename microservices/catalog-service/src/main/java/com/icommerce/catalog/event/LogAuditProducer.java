package com.icommerce.catalog.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.icommerce.catalog.dto.Event;
import com.icommerce.catalog.dto.SearchCriteria;

@Service
public class LogAuditProducer {
  public static final String DESTINATION = "audit-support";

  @Autowired 
  private StreamBridge bridge;

  public void logAudit(SearchCriteria criteria, Long productId, Event.Type event) {
    bridge.send(DESTINATION, new Event(event, productId, criteria));
  }

}