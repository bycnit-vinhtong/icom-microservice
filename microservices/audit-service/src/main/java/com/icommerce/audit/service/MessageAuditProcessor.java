package com.icommerce.audit.service;

import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icommerce.audit.dto.AuditLogDto;
import com.icommerce.audit.dto.Event;
import com.icommerce.audit.dto.SearchCriteria;
import java.util.function.Consumer;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MessageAuditProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(MessageAuditProcessor.class);

	@Autowired
	AuditLogService auditLogService;

   	@Bean
   	public Consumer<Message<Event<Integer, SearchCriteria>>> catalogAudit() {
      return event -> {
		 process(event.getPayload());
      };
   	}

	public void process(Event<Integer, SearchCriteria> event) {
		if (event != null && event.getEventType() != null) {
			LOG.info("Process message created at {}...", event.getEventCreatedAt());
			AuditLogDto dto = null;
			switch (event.getEventType()) {

			case SEARCH:
				try {
					SearchCriteria searchCriteria = event.getData();
					LOG.info("Find products with criteria: {}", searchCriteria);

					ObjectMapper Obj = new ObjectMapper();
					String searchString = Obj.writeValueAsString(searchCriteria);

					dto = new AuditLogDto();
					dto.setAction("Search");
					dto.setData(searchString);
					dto.setTime(event.getEventCreatedAt());

					
				} catch (IOException ex) {
					LOG.error(ex.getMessage(), ex);
				}

				break;

			case VIEW:
				int productId = event.getKey();
				LOG.info("View a product with ProductID: {}", productId);

				dto = new AuditLogDto();
				dto.setAction("View");
				dto.setData(event.getKey() != null ? String.valueOf(event.getKey()) : "");
				dto.setTime(event.getEventCreatedAt());

				break;

			default:
				String errorMessage = "Incorrect event type: " + event.getEventType()
						+ ", expected a SEARCH or VIEW event";
				LOG.warn(errorMessage);

			}
			
			if (dto != null) {
				auditLogService.createAuditLog(dto);
			}

			LOG.info("Message processing done!");
		}

	}
}
