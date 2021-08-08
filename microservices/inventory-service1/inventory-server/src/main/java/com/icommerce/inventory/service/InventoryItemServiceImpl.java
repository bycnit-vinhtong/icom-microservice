package com.icommerce.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icommerce.inventory.client.InventoryItemDto;
import com.icommerce.inventory.client.InventoryResponseDTO;
import com.icommerce.inventory.client.InventoryStatus;
import com.icommerce.inventory.client.InventorytRequestDto;
import com.icommerce.inventory.domain.InventoryItem;
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

	@Override
	public InventoryResponseDTO deductInventory(InventorytRequestDto requestDTO) {
		int quantity = 0;
				
		Optional<InventoryItem> itemOpt = inventoryItemRepository.findByProductId(requestDTO.getProductId());
		InventoryItem item = null;
		if (itemOpt.isPresent()) {
			item = itemOpt.get();
			quantity = item.getAvailableQuantity();
		}
		
		InventoryResponseDTO responseDTO = new InventoryResponseDTO();
		responseDTO.setOrderId(requestDTO.getOrderId());
		responseDTO.setUserId(requestDTO.getUserId());
		responseDTO.setProductId(requestDTO.getProductId());
		responseDTO.setStatus(InventoryStatus.UNAVAILABLE);
		
		if (quantity > 0) {
			responseDTO.setStatus(InventoryStatus.AVAILABLE);
			item.setAvailableQuantity(item.getAvailableQuantity() - 1);
			inventoryItemRepository.save(item);
		}
		
		return responseDTO;
	}

	@Override
	public void addInventory(InventorytRequestDto requestDTO) {
		Optional<InventoryItem> itemOpt = inventoryItemRepository.findByProductId(requestDTO.getProductId());
		InventoryItem item = null;
		if (itemOpt.isPresent()) {
			item = itemOpt.get();
			item.setAvailableQuantity(item.getAvailableQuantity() + 1);
			inventoryItemRepository.save(item);
		}
	}

}
