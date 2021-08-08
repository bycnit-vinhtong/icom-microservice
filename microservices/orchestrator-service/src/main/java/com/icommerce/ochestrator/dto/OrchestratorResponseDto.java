package com.icommerce.ochestrator.dto;

import lombok.Data;

@Data
public class OrchestratorResponseDto {

    private Long userId;
    private Long productId;
    private Long orderId;
    private Double amount;
    private OrderStatus status;

}
