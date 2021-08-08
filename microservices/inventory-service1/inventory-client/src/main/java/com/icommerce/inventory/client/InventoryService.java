package com.icommerce.inventory.client;

import org.springframework.web.client.RestTemplate;

public class InventoryService {
	public static InventoryClient client(RestTemplate restTemplate) {
		return new InventoryClientImpl(restTemplate);
	}
}
