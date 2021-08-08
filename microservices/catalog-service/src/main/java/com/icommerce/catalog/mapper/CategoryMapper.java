package com.icommerce.catalog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.icommerce.catalog.domain.Category;
import com.icommerce.catalog.dto.CategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	CategoryDto entityToDto(Category category);

	Category dtoToEntity(CategoryDto categoryDto);
	
}
