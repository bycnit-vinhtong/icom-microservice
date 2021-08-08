package com.icommerce.inventory.client;

import lombok.Data;

@Data
public class InventoryResponseDTO {

	private Long orderId;
	
	private Long userId;
	
	private Long productId;
	
	private InventoryStatus status;

}
