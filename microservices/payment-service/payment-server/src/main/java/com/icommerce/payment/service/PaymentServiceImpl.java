package com.icommerce.payment.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.icommerce.payment.client.PaymentRequestDto;
import com.icommerce.payment.client.PaymentResponseDTO;
import com.icommerce.payment.client.PaymentStatus;

@Service
public class PaymentServiceImpl implements PaymentService {
    
    private Map<Long, Double> userBalanceMap;

    @PostConstruct
    private void init(){
        this.userBalanceMap = new HashMap<>();
        this.userBalanceMap.put(1l, 1000d);
        this.userBalanceMap.put(2l, 1000d);
        this.userBalanceMap.put(3l, 1000d);
    }

    public PaymentResponseDTO debit(final PaymentRequestDto requestDTO){
        double balance = this.userBalanceMap.getOrDefault(requestDTO.getUserId(), 0d);
        PaymentResponseDTO responseDTO = new PaymentResponseDTO();
        responseDTO.setAmount(requestDTO.getAmount());
        responseDTO.setUserId(requestDTO.getUserId());
        responseDTO.setOrderId(requestDTO.getOrderId());
        responseDTO.setStatus(PaymentStatus.PAYMENT_REJECTED);
        if(balance >= requestDTO.getAmount()){
            responseDTO.setStatus(PaymentStatus.PAYMENT_APPROVED);
            this.userBalanceMap.put(requestDTO.getUserId(), balance - requestDTO.getAmount());
        }
        return responseDTO;
    }

    public void credit(final PaymentRequestDto requestDTO){
        this.userBalanceMap.computeIfPresent(requestDTO.getUserId(), (k, v) -> v + requestDTO.getAmount());
    }
    
    public Double getCurrentBalance(Long userId) {
        return this.userBalanceMap.get(userId);
    }
}
