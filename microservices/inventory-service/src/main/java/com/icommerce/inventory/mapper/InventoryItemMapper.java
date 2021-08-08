package com.icommerce.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.icommerce.inventory.domain.InventoryItem;
import com.icommerce.inventory.dto.InventoryItemDto;


@Mapper(componentModel = "spring")
public interface InventoryItemMapper {
	
	InventoryItemMapper INSTANCE = Mappers.getMapper(InventoryItemMapper.class);
	
	InventoryItemDto entityToDto(InventoryItem inventoryItem);

	InventoryItem dtoToEntity(InventoryItemDto inventoryItemDto);
	
}
