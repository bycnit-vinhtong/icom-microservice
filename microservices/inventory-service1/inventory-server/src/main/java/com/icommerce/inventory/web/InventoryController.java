package com.icommerce.inventory.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icommerce.inventory.client.InventoryItemDto;
import com.icommerce.inventory.client.InventoryResponseDTO;
import com.icommerce.inventory.client.InventorytRequestDto;
import com.icommerce.inventory.constants.WebConstants;
import com.icommerce.inventory.service.InventoryItemService;


@RestController
@RefreshScope
public class InventoryController {
	
	private static final Logger LOG = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	InventoryItemService inventoryItemService;
	
	@Value("${app.message}")
	private String appMessage;
	
	
	/**
	 * Returns the list of all Inventory item
	 *
	 * @return a list of Inventory item
	 */
	@GetMapping
	public List<InventoryItemDto> getAll() {
		LOG.info("Get all Inventory Item ");
		return inventoryItemService.getAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public InventoryItemDto get(@PathVariable("id") final Long id) {
		LOG.info("Get product...{} ", id);
		return inventoryItemService.getByProductId(id);
	}

	@GetMapping("/app-message")
    public String getServiceName() {
        return "Message [" + this.appMessage + "]";
    }	
	
	@PostMapping("/deduct")
    public InventoryResponseDTO deduct(@RequestBody final InventorytRequestDto requestDTO){
        return this.inventoryItemService.deductInventory(requestDTO);
    }

    @PostMapping("/add")
    public void add(@RequestBody final InventorytRequestDto requestDTO){
        this.inventoryItemService.addInventory(requestDTO);
    }

	
}
