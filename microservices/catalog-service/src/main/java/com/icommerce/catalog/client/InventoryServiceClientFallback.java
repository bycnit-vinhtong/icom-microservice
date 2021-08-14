package com.icommerce.catalog.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.icommerce.inventory.client.InventoryItemDto;

/*@Component
public class InventoryServiceClientFallback implements InventoryFeignServiceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceClientFallback.class);
    
	@Override
	public InventoryItemDto getInventory(Long productId) {
		LOGGER.error("Error during get quantity for Product: {}", productId);
		return null;
	}
}*/
