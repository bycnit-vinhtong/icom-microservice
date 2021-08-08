package com.icommerce.inventory.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItemDto {

    private Long id;
	
	private String productCode;
	
	private Long productId;
	
	private Integer availableQuantity = 0;
	
}
