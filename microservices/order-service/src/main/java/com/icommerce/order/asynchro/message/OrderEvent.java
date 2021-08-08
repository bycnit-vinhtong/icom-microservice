package com.icommerce.order.asynchro.message;

import com.icommerce.order.constants.OrderAction;

public class OrderEvent {
	
	private Long orderId;
	
	private OrderAction action;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public OrderAction getAction() {
		return action;
	}
	public void setAction(OrderAction action) {
		this.action = action;
	}

}
