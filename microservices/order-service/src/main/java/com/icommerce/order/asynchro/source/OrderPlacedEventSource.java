package com.icommerce.order.asynchro.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import com.icommerce.order.asynchro.chanel.OrderChannel;
import com.icommerce.order.dto.OrchestratorRequestDTO;

@Component
public class OrderPlacedEventSource {
	
	@Autowired
	private OrderChannel orderChannel;
	
	public void publishOrderEvent(OrchestratorRequestDTO orderEvent) {

		MessageChannel messageChannel = orderChannel.outboundOrder();
		
		messageChannel.send(MessageBuilder.withPayload(orderEvent)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				.build());
	}

}

