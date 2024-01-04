package com.icommerce.inventory.web;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
import com.icommerce.inventory.service.InventoryItemService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


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

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping("/app-message")
    public String getServiceName(Authentication authentication) {
		final String userName = authentication.getName();
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = authorities.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		
        return "Message " + this.appMessage + " " + userName + " Roles: " + roles + "";
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
