package com.icommerce.order.dto;

import lombok.Data;

@Data
public class PlaceOrderRequestDto {

	private Long productId;
	
	private Long userId;
	
	private Long quantity;

}
