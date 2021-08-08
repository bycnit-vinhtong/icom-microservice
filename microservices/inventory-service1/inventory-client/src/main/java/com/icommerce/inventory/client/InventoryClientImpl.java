package com.icommerce.inventory.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class InventoryClientImpl implements InventoryClient {

	private RestTemplate restTemplate;
	
	String serviceUrl = "http://localhost:7002/inventory";
	

	public InventoryClientImpl(RestTemplate restTemplate) {

		this.restTemplate = restTemplate;
	}

	
	@Override
	public InventoryItemDto getInventory(InventorytRequestDto request) {
		
		InventoryItemDto inventoryResponse = restTemplate.getForObject(serviceUrl + "/" + request.getProductId(), InventoryItemDto.class);
		
		return inventoryResponse;
	}


	@Override
	public InventoryResponseDTO deductInventory(InventorytRequestDto requestDTO) {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<InventorytRequestDto> request = 
        	      new HttpEntity<InventorytRequestDto>(requestDTO, headers);
        return restTemplate.postForObject(serviceUrl + "/deduct", request, InventoryResponseDTO.class);
	}

	
	@Override
	public void addInventory(InventorytRequestDto requestDTO) {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<InventorytRequestDto> request = 
        	      new HttpEntity<InventorytRequestDto>(requestDTO, headers);
        restTemplate.postForObject(serviceUrl + "/add", request, InventoryResponseDTO.class);
		
	}
}
