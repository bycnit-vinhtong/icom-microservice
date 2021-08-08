package com.icommerce.catalog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.icommerce.catalog.client.InventoryFeignServiceClient;
import com.icommerce.inventory.client.InventoryItemDto;
import com.icommerce.inventory.client.InventoryService;
import com.icommerce.inventory.client.InventorytRequestDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ProductInventoryService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);


	@Autowired
	OAuth2RestTemplate orestTemplate;

	@Value("${app.inventory-service.host}")
	private String inventoryServiceUri;

	@Autowired
	InventoryFeignServiceClient inventoryFeignServiceClient;

	// Demo SYCN request to inventory service via Discovery Service name
	//@HystrixCommand(fallbackMethod = "fallback")
	public int getInventory(@PathVariable("id") final Long productId) {
		LOG.info("getting inventory object ... ");
		// get Basket
		InventoryItemDto inventory = inventoryFeignServiceClient
				.getInventory(productId);
		LOG.info("Returning inventory fallback feign client... ");
		return inventory != null ? inventory.getAvailableQuantity() : 0;
	}
	
	// Demo SYCN request to inventory service via Discovery Service name
	//@HystrixCommand(fallbackMethod = "fallback")
	public int getInventoryUsingRestTemplate(final Long productId) {
		LOG.info("getting inventory object ... ");
		// get Basket
		InventoryItemDto inventory = orestTemplate.getForObject(inventoryServiceUri + "/" + productId,
				InventoryItemDto.class);

		LOG.info("Returning inventory ... ");
		return inventory.getAvailableQuantity();
	}
	
	@HystrixCommand(fallbackMethod = "fallback")
	public com.icommerce.inventory.client.InventoryItemDto getInventoryUsingSharedClientPackage(final Long productId) {
		LOG.info("Returning inventory fallback rest client... ");
		return InventoryService.client(orestTemplate).getInventory(new InventorytRequestDto(1L , "PD1", 1L, 1L));
	}

	public com.icommerce.inventory.client.InventoryItemDto fallback(Long productId) {
		LOG.info("Returning fall ... ");
		return new com.icommerce.inventory.client.InventoryItemDto();
	}

}
