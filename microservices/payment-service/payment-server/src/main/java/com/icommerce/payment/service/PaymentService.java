package com.icommerce.payment.service;

import com.icommerce.payment.client.PaymentRequestDto;
import com.icommerce.payment.client.PaymentResponseDTO;

public interface PaymentService {
    PaymentResponseDTO debit(PaymentRequestDto requestDTO);

    void credit(PaymentRequestDto requestDTO);
    
    Double getCurrentBalance(Long userId);
}
