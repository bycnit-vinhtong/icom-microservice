package com.icommerce.inventory;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.icommerce.inventory.client.InventoryItemDto;
import com.icommerce.inventory.service.InventoryItemService;
import com.icommerce.inventory.web.InventoryController;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InventoryServiceApplication.class)
public abstract class BaseClass {

	@Autowired
	InventoryController inventoryController;

	@MockBean
	InventoryItemService inventoryItemService;

	@Before
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(inventoryController);

		Mockito.when(inventoryItemService.getByProductId(1L)).thenReturn(new InventoryItemDto(1L, "PD01", 1L, 10));
	}

}
