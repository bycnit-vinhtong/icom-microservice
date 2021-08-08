package com.icommerce.order.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icommerce.order.constants.WebConstants;
import com.icommerce.order.dto.PlaceOrderRequestDto;
import com.icommerce.order.dto.PlaceOrderResponseDto;
import com.icommerce.order.service.OrderService;


@RestController
@RequestMapping(WebConstants.View.ORDER)
@RefreshScope
public class OrderController {
	
	private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

	@Value("${app.message}")
	private String appMessage;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping()
	public PlaceOrderResponseDto placeOrder(@RequestBody PlaceOrderRequestDto request) {
		LOG.info("Request order: {}", request);
		return orderService.createOrder(request);
	}
	
	@GetMapping()
    public List<PlaceOrderResponseDto> getOrders() {
		return this.orderService.getAll();
    }	
	
	
	@GetMapping("/app-message")
    public String getServiceName() {
        return "Message [" + this.appMessage + "]";
    }	
	
}
