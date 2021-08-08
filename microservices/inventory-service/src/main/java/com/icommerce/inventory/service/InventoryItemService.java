package com.icommerce.inventory.service;

import java.util.List;

import com.icommerce.inventory.dto.InventoryItemDto;

public interface InventoryItemService {
	
	InventoryItemDto getByProductId(Long productId);
	
	List<InventoryItemDto> getAll();
	
}
