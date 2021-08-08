package com.icommerce.order.dto;

import lombok.Data;

@Data
public class InventoryItemDto {
	
	private Long id;
	
	private String productCode;
	
	private Long productId;
	
	private Integer availableQuantity = 0;
}
