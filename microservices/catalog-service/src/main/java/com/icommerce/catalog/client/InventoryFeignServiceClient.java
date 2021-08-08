package com.icommerce.catalog.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.icommerce.inventory.client.InventoryItemDto;

@FeignClient(name = "inventory-service", fallback = InventoryServiceClientFallback.class)
public interface InventoryFeignServiceClient {

	@RequestMapping(method = RequestMethod.GET, value = "/inventory/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	InventoryItemDto getInventory(@PathVariable("id") final Long productId);

}
