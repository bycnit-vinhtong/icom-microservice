package com.icommerce.payment.client;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {
	
	private Long userId;
	
	private Double amount;
	
    private Long orderId;

}
