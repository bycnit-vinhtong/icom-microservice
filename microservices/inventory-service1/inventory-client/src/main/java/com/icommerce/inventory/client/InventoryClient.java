package com.icommerce.inventory.client;

public interface InventoryClient {

	InventoryItemDto getInventory(InventorytRequestDto request);

	InventoryResponseDTO deductInventory(final InventorytRequestDto requestDTO);

	void addInventory(final InventorytRequestDto requestDTO);

}
