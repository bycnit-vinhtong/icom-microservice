package com.icommerce.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icommerce.inventory.domain.InventoryItem;
import com.icommerce.inventory.dto.InventoryItemDto;
import com.icommerce.inventory.mapper.InventoryItemMapper;
import com.icommerce.inventory.repository.InventoryItemRepository;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {

	@Autowired
	InventoryItemRepository inventoryItemRepository;

	@Autowired
	InventoryItemMapper mapper;

	@Override
	public InventoryItemDto getByProductId(Long productId) {
		Optional<InventoryItem> item = inventoryItemRepository.findByProductId(productId);
		if (item.isPresent()) {
			return mapper.entityToDto(item.get());
		}
		return null;
	}

	@Override
	public List<InventoryItemDto> getAll() {
		List<InventoryItemDto> result = new ArrayList<>();
		inventoryItemRepository.findAll().forEach(item -> {
			result.add(mapper.entityToDto(item));
		});
		return result;
	}

}
