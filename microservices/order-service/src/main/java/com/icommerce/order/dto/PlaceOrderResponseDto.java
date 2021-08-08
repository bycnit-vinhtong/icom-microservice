package com.icommerce.order.dto;

import com.icommerce.order.constants.OrderStatus;

import lombok.Data;

@Data
public class PlaceOrderResponseDto {

	private Long orderId;
	
	private Long userId;
	
	private Long productId;
	
	private Double amount;
	
	private OrderStatus status;
	
	private String message;

}
