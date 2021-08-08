package com.icommerce.catalog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.icommerce.catalog.domain.Brand;
import com.icommerce.catalog.dto.BrandDto;

@Mapper(componentModel = "spring")
public interface BrandMapper {
	
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
	
	BrandDto entityToDto(Brand brand);

	Brand dtoToEntity(BrandDto brandDto);
	
}
