package com.icommerce.inventory.service;

import java.util.List;

import com.icommerce.inventory.client.InventoryItemDto;
import com.icommerce.inventory.client.InventoryResponseDTO;
import com.icommerce.inventory.client.InventorytRequestDto;

public interface InventoryItemService {
	
	InventoryItemDto getByProductId(Long productId);
	
	List<InventoryItemDto> getAll();
	
	InventoryResponseDTO deductInventory(final InventorytRequestDto requestDTO);
	
	void addInventory(final InventorytRequestDto requestDTO);
}
