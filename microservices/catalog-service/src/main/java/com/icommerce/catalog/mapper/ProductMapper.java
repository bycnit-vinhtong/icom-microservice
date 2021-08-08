package com.icommerce.catalog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.icommerce.catalog.domain.Product;
import com.icommerce.catalog.dto.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
	ProductDto entityToDto(Product product);

	Product dtoToEntity(ProductDto productDto);
}
