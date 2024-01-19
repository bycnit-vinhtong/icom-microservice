package com.icommerce.catalog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import com.icommerce.catalog.client.InventoryFeignServiceClient;
import com.icommerce.inventory.client.InventoryItemDto;
import com.icommerce.inventory.client.InventoryService;
import com.icommerce.inventory.client.InventorytRequestDto;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.annotation.PostConstruct;

@Service
public class ProductInventoryService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductInventoryService.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${app.inventory-service.host}")
	private String inventoryServiceUri;

	/*@Autowired
	InventoryFeignServiceClient inventoryFeignServiceClient;*/

	// Demo SYCN request to inventory service via Discovery Service name
	//@HystrixCommand(fallbackMethod = "fallback")
	/*public int getInventory(@PathVariable("id") final Long productId) {
		LOG.info("getting inventory object ... ");
		// get Basket
		InventoryItemDto inventory = inventoryFeignServiceClient
				.getInventory(productId);
		LOG.info("Returning inventory fallback feign client... ");
		return inventory != null ? inventory.getAvailableQuantity() : 0;
	}*/
	
	// Demo SYCN request to inventory service via Discovery Service name
	@Retry(name = "default")
	@Bulkhead(name = "inventoryService")
	@CircuitBreaker(name = "default", fallbackMethod = "fallback")
	public int getInventoryUsingRestTemplate(final Long productId) {
		LOG.info("getting inventory object ... ");
		// get Basket
		InventoryItemDto inventory = restTemplate.getForObject(inventoryServiceUri + "/" + productId,
				InventoryItemDto.class);

		LOG.info("Returning inventory ... ");
		return inventory.getAvailableQuantity();
	}
	
	public com.icommerce.inventory.client.InventoryItemDto getInventoryUsingSharedClientPackage(final Long productId) {
		LOG.info("Returning inventory fallback rest client... ");
		return InventoryService.client(restTemplate).getInventory(new InventorytRequestDto(1L , "PD1", 1L, 1L));
	}

	public int fallback(Long productId, CallNotPermittedException e) {
		LOG.info("Returning fall ... ");
		return 0;
	}



}
