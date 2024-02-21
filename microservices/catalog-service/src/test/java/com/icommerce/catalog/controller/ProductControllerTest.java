package com.icommerce.catalog.controller;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.nio.file.Paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icommerce.catalog.CatalogServiceTestsApp;
import com.icommerce.catalog.dto.BrandDto;
import com.icommerce.catalog.dto.CategoryDto;
import com.icommerce.catalog.dto.ProductShoppingResponseDto;
import com.icommerce.catalog.service.ProductService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=RANDOM_PORT,  properties = {"eureka.client.enabled=false"},classes = {CatalogServiceTestsApp.class})
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ProductControllerTest {
	
	@Autowired
	private ProductService productService;
	
	@MockBean
	OAuth2RestTemplate orestTemplate;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testInventoryClient() {
		ProductShoppingResponseDto product = productService.getProduct(1L);
		//assertEquals(1L, product.getId());
	}
	
	//@Test
	@DisplayName("GET product/1 - Found")
	public void testGetApplicaitonByIdFound() throws Exception {
		
		ProductShoppingResponseDto mockProductDto = new ProductShoppingResponseDto();
		mockProductDto.setProductCode("PD1");
		mockProductDto.setName("Badminton bracket1");
		mockProductDto.setPrice( new BigDecimal(50.5));
		mockProductDto.setDescription("Very nice bracker");
		mockProductDto.setPictureFileName("picture1.jpg");
		mockProductDto.setPictureUri("/images/picture1.jpg");
		BrandDto brand = new BrandDto();
		brand.setId(1L);
		mockProductDto.setBrand(brand);
		CategoryDto category = new CategoryDto();
		category.setId(1L);
		mockProductDto.setCategory(category);
		mockProductDto.setColor("read");

		Mockito.doReturn(mockProductDto).when(productService).getProduct(1L);
		
		mockMvc.perform(get("/product/{id}", 1))
		.andExpect(status().isOk())
		.andExpect(content().json(asJsonString(mockProductDto)));
	}
	
	
	static String asJsonString(final Object obj) {
		try {
			
			return new ObjectMapper().writeValueAsString(obj);
		}
		catch( Exception e ) {
			throw new RuntimeException(e);
		}
	}

	static final String CURRENT_DIR = "";
	@Test
	public void whenUsingJavaNioPaths_thenReturnCurrentDirectory() {
		String userDirectory = Paths.get("")
			.toAbsolutePath()
			.toString();
		System.out.println(userDirectory);
		assertTrue(userDirectory.endsWith(CURRENT_DIR));
	}
	
}
