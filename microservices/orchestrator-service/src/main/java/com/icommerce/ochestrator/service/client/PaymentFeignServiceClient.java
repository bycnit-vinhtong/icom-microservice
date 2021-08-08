package com.icommerce.ochestrator.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import com.icommerce.payment.client.PaymentRequestDto;
import com.icommerce.payment.client.PaymentResponseDTO;

@FeignClient(name = "payment-service",url = "${feign.client.url.paymentService}", fallback = PaymentServiceClientFallback.class)
public interface PaymentFeignServiceClient {

    @PostMapping(path = "/payment/debit", consumes = MediaType.APPLICATION_JSON_VALUE)
    PaymentResponseDTO debit(PaymentRequestDto paymentRequestDto);

	@PostMapping(path = "/payment/credit", consumes = MediaType.APPLICATION_JSON_VALUE)
	void credit(PaymentRequestDto paymentRequestDto);
	
}
