package com.icommerce.ochestrator.dto;

import lombok.Data;

@Data
public class OrchestratorRequestDto {
	
	private Long orderId;
	
	
    private Long userId;
    
    private Long productId;
    
    private Double amount;
	
	private OrderAction action;
	
	public static enum OrderAction {
		ORDERPLACED,
		ORDERNOTPLACED
	}

}
