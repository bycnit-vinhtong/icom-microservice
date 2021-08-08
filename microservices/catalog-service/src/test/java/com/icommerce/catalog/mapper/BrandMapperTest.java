package com.icommerce.catalog.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.icommerce.catalog.domain.Brand;
import com.icommerce.catalog.dto.BrandDto;
import com.icommerce.catalog.mapper.BrandMapper;

public class BrandMapperTest {

	@Test
	public void givenBrandEntitytoBrandDto_whenMaps_thenCorrect() {

		Brand entity = new Brand();
		entity.setId(1L);
		entity.setName("Toyota");

		BrandDto brandDto = Mappers.getMapper(BrandMapper.class).entityToDto(entity);

		assertEquals(brandDto.getId().longValue(), entity.getId().longValue());
		assertEquals(brandDto.getName(), entity.getName());

	}
	
	@Test
	public void givenBrandDtoToEntity_whenMaps_thenCorrect() {

		BrandDto dto = new BrandDto();
		dto.setId(1L);
		dto.setName("Toyota");

		Brand brand = Mappers.getMapper(BrandMapper.class).dtoToEntity(dto);

		assertEquals(brand.getId().longValue(), dto.getId().longValue());
		assertEquals(brand.getName(), dto.getName());

	}
}
