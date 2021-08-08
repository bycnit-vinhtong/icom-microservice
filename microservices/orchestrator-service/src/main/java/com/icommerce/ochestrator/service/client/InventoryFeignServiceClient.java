package com.icommerce.ochestrator.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.icommerce.inventory.client.InventoryItemDto;
import com.icommerce.inventory.client.InventoryResponseDTO;
import com.icommerce.inventory.client.InventorytRequestDto;

@FeignClient(name = "inventory-service",url = "${feign.client.url.inventoryService}", fallback = InventoryServiceClientFallback.class)
public interface InventoryFeignServiceClient {

	@GetMapping(path = "/inventory/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	InventoryItemDto getInventory(@PathVariable("id") final Long productId);

	@PostMapping(path = "/inventory/deduct", consumes = MediaType.APPLICATION_JSON_VALUE)
	InventoryResponseDTO deductInventory(@RequestBody  final InventorytRequestDto requestDto);
	
	@PostMapping(path = "/inventory/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	void addInventory(@RequestBody  final InventorytRequestDto requestDto);
	
}
