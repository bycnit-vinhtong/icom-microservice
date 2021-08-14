package com.icommerce.catalog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.icommerce.catalog.dto.ProductDto;

@SpringBootTest
@RunWith(PowerMockRunner.class)
public class ProductServiceTest2 {

	@InjectMocks
	ProductServiceImpl productService;
	
	@Mock
	ProductInventoryService productInventoryService;


	@Test
	@DisplayName("Test get an existing product")
	public void testGetProductFound() {
		Long productId = 1L;
		Mockito.doReturn(0).when(productInventoryService).getInventory(1L);
		ProductDto result = productService.getProduct(productId);
		Assertions.assertNotNull(result, "The product must existing");
		assertEquals("1", String.valueOf(result.getId()), "The product must equal 1");
		assertEquals("PD1", result.getProductCode(), "The product must equal PD1");
		assertEquals("Badminton bracket1", result.getName());
	}
	
	
}
