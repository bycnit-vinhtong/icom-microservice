package com.icommerce.ochestrator.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.icommerce.payment.client.PaymentRequestDto;
import com.icommerce.payment.client.PaymentResponseDTO;

@Component
public class PaymentServiceClientFallback implements PaymentFeignServiceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceClientFallback.class);

    @Override
    public PaymentResponseDTO debit(PaymentRequestDto paymentRequestDto) {
        LOGGER.info("Error during debit payment {}", paymentRequestDto);
        return null;
    }

    @Override
    public void credit(PaymentRequestDto paymentRequestDto) {
        LOGGER.info("Error during credit payment {}", paymentRequestDto);
    }
}
