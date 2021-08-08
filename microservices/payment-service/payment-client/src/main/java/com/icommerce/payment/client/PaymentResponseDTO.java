package com.icommerce.payment.client;

import lombok.Data;

@Data
public class PaymentResponseDTO {

    private Long orderId;

    private Long userId;

    private Double amount;

    private PaymentStatus status;

}
