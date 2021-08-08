package com.icommerce.inventory.client;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventorytRequestDto {
	
	private Long userId;
	
	private String productCode;
	
    private Long productId;
    
    private Long orderId;

}
