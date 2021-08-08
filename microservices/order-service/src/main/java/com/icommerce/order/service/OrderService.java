package com.icommerce.order.service;

import java.util.List;

import com.icommerce.order.dto.PlaceOrderResponseDto;
import com.icommerce.order.dto.PlaceOrderRequestDto;

public interface OrderService {
	public PlaceOrderResponseDto createOrder(PlaceOrderRequestDto request);
	
	public void compensateOrder(Long orderId) ;
	
	public List<PlaceOrderResponseDto> getAll();
}
