package com.icommerce.ochestrator.asynchro.chanel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrchestratorChannel {

	String INPUT_ORDER = "order-input";
	
	String INPUT_INVNETORY = "inventory-input";
	
	String INPUT_PAYMENT = "payment-input";

	@Input(INPUT_ORDER)
	SubscribableChannel inboundOrder();
	
	/*@Input(INPUT_INVNETORY)
	SubscribableChannel inboundInventory();
	
	@Input(INPUT_PAYMENT)
	SubscribableChannel inboundPayment();*/

}
