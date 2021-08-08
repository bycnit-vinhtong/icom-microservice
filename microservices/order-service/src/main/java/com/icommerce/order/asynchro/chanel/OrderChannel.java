package com.icommerce.order.asynchro.chanel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderChannel {

	String OUTPUT_ORDER = "order-output";

	@Output(OUTPUT_ORDER)
	MessageChannel outboundOrder();

}
