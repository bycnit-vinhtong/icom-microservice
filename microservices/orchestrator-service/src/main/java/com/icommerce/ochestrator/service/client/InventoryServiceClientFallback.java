package com.icommerce.ochestrator.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.icommerce.inventory.client.InventoryItemDto;
import com.icommerce.inventory.client.InventoryResponseDTO;
import com.icommerce.inventory.client.InventorytRequestDto;

@Component
public class InventoryServiceClientFallback implements InventoryFeignServiceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceClientFallback.class);
    
	@Override
	public InventoryItemDto getInventory(Long productId) {
		LOGGER.error("Error during get quantity for Product: {}", productId);
		return null;
	}

	@Override
	public InventoryResponseDTO deductInventory(InventorytRequestDto requestDto) {
		LOGGER.error("Error during deduct quantity for Product: {}", requestDto.getProductId());
		return null;
	}

	@Override
	public void addInventory(InventorytRequestDto requestDto) {
		LOGGER.error("Error during add quantity for Product: {}", requestDto.getProductId());
	}
}
