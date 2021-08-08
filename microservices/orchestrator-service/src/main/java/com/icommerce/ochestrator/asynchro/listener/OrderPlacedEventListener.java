package com.icommerce.ochestrator.asynchro.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.icommerce.ochestrator.asynchro.chanel.OrchestratorChannel;
import com.icommerce.ochestrator.dto.OrchestratorRequestDto;
import com.icommerce.ochestrator.service.OrchestratorService;

@Component
public class OrderPlacedEventListener {

    private static final Logger logger = LoggerFactory.getLogger(OrderPlacedEventListener.class);
    
    @Autowired
    OrchestratorService orchestratorService;

    @StreamListener(target = OrchestratorChannel.INPUT_ORDER)
    public void listenOrderPlaced(@Payload OrchestratorRequestDto orderRequestDto) {

        if (OrchestratorRequestDto.OrderAction.ORDERPLACED.equals(orderRequestDto.getAction())) {
            logger.info("Received an OrderPlacedEvent for order id: {}", orderRequestDto.getOrderId());
            orchestratorService.orderProduct(orderRequestDto);
        }
    }


}
