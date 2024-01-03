package com.icommerce.catalog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.icommerce.catalog.CatalogServiceTestsApp;
import com.icommerce.catalog.dto.PageDto;
import com.icommerce.catalog.dto.ProductDto;
import com.icommerce.catalog.dto.SearchCriteria;

import static org.mockito.ArgumentMatchers.anyLong;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=RANDOM_PORT, properties = {"eureka.client.enabled=false"},classes = {CatalogServiceTestsApp.class})
public class ProductServiceTest {

	@MockBean
	private RabbitAdmin rabbitAdmin;
	
	@Autowired
	ProductService productService;
	
	@MockBean
	ProductInventoryService productInventoryService;

	@Mock
    private RestTemplate restTemplate;
	
	

	@Test
	@DisplayName("Test get an existing product")
	void testGetProductFound() {
		Long productId = 1L;
		Mockito.doReturn(0).when(productInventoryService).getInventoryUsingRestTemplate(1L);
		ProductDto result = productService.getProduct(productId);
		Assertions.assertNotNull(result, "The product must existing");
		assertEquals("1", String.valueOf(result.getId()), "The product must equal 1");
		assertEquals("PD1", result.getProductCode(), "The product must equal PD1");
		assertEquals("Badminton bracket1", result.getName());
	}
	@Test
	@DisplayName("Test get an non existing product")
	void testGetProductNotFound() {
		Long productId = 1000L;
		
		try {
			productService.getProduct(productId);
			fail("The product with id 1000 must not be exisiting");
		}catch(Exception ex) {
			//no need to handle
		}
	}
	
	@Test
	@DisplayName("Test found product by name")
	void testFoundProductByName() {
		Mockito.doReturn(0).when(productInventoryService).getInventoryUsingRestTemplate(anyLong());
		SearchCriteria searchCriteria = new SearchCriteria("bracket");
		Map<String, Object> filters = new HashMap<>();
		searchCriteria.setFilters(filters);
		PageDto<ProductDto> result = productService.findProductsByCriterias(searchCriteria);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(2L, result.getTotalElements());
	}
	
	@Test
	@DisplayName("Test notfound product by name")
	void testNotFoundProductByName() {
		Mockito.doReturn(0).when(productInventoryService).getInventoryUsingRestTemplate(anyLong());
		SearchCriteria searchCriteria = new SearchCriteria("abasdf asdf");
		Map<String, Object> filters = new HashMap<>();
		searchCriteria.setFilters(filters);
		PageDto<ProductDto> result = productService.findProductsByCriterias(searchCriteria);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(0L, result.getTotalElements());
	}
	
	@Test
	@DisplayName("Test found product filter by brand and category")
	void testFoundProductByNameAndFilterByBrandAndCategory() {
		Mockito.doReturn(0).when(productInventoryService).getInventoryUsingRestTemplate(anyLong());
		SearchCriteria searchCriteria = new SearchCriteria("");
		Map<String, Object> filters = new HashMap<>();
		filters.put("brand", 1);
		filters.put("category", 1);
		searchCriteria.setFilters(filters);
		PageDto<ProductDto> result = productService.findProductsByCriterias(searchCriteria);
		Assertions.assertNotNull(result);
		if(result.getTotalPages() > 0) {
			Assertions.assertEquals(1, result.getContent().get(0).getBrand().getId().longValue());
			Assertions.assertEquals(1, result.getContent().get(0).getCategory().getId().longValue());
		}
	}
	
	@Test
	@DisplayName("Test found product by name and filter by brand and category")
	void testFoundProductByNameAndFilterByPrice() {
		Mockito.doReturn(0).when(productInventoryService).getInventoryUsingRestTemplate(anyLong());
	}
	
	@Test
	@DisplayName("Test found product by name and filter by all properties")
	void testFoundProductByNameAndFilterByAllProperties() {
		Mockito.doReturn(0).when(productInventoryService).getInventoryUsingRestTemplate(anyLong());
	}

}
