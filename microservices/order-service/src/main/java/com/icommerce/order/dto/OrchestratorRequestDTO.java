package com.icommerce.order.dto;

import lombok.Data;

@Data
public class OrchestratorRequestDTO {
	
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
