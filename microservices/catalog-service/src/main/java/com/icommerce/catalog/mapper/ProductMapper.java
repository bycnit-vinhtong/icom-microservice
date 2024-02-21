package com.icommerce.catalog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.icommerce.catalog.domain.Product;
import com.icommerce.catalog.dto.ProductInfoResponseDto;
import com.icommerce.catalog.dto.ProductShoppingResponseDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
	ProductShoppingResponseDto entityToShoppingDto(Product product);

	Product shoppingDtoToEntity(ProductShoppingResponseDto productDto);

	@Mapping(target = "brand", source = "brand.name")
	@Mapping(target = "category", source = "category.name")
	ProductInfoResponseDto entityToInfoDto(Product product);
}
