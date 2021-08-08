package com.icommerce.payment.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icommerce.payment.client.PaymentRequestDto;
import com.icommerce.payment.client.PaymentResponseDTO;
import com.icommerce.payment.constants.WebConstants;
import com.icommerce.payment.service.PaymentService;


@RestController
@RequestMapping(WebConstants.View.PAYMENT)
@RefreshScope
public class PaymentController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	PaymentService inventoryItemService;
	
	@Value("${app.message}")
	private String appMessage;
	
	
	@Autowired
    private PaymentService service;

    @PostMapping("/debit")
    public PaymentResponseDTO debit(@RequestBody PaymentRequestDto requestDTO){
        LOG.info("Payment debit request {} ", requestDTO);
        return this.service.debit(requestDTO);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody PaymentRequestDto requestDTO){
        LOG.info("Payment credit request {} ", requestDTO);
        this.service.credit(requestDTO);
    }
    
    @GetMapping("/balance/{id}")
    public Double getCurrentBalance(@PathVariable Long id){
        return this.service.getCurrentBalance(id);
    }

	
}
