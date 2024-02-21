package com.icommerce.catalog.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.icommerce.catalog.domain.Brand;
import com.icommerce.catalog.domain.Product;
import com.icommerce.catalog.dto.BrandDto;
import com.icommerce.catalog.dto.ProductShoppingResponseDto;
import com.icommerce.catalog.mapper.ProductMapper;

public class ProductMapperTest {

	@Test
	public void givenProductEntitytoProductDto_whenMaps_thenCorrect() {

		Product entity = new Product();
		entity.setId(1L);
		entity.setName("Fortuner");
		
		Brand brand = new Brand();
		brand.setId(1L);
		brand.setName("Toyota");
		
		entity.setBrand(brand);

		ProductShoppingResponseDto productDto = Mappers.getMapper(ProductMapper.class).entityToDto(entity);

		assertEquals(productDto.getId().longValue(), entity.getId().longValue());
		assertEquals(productDto.getName(), entity.getName());
	}
	
	@Test
	public void givenProductDtoToProductEntity_whenMaps_thenCorrect() {

		ProductShoppingResponseDto productDto = new ProductShoppingResponseDto();
		productDto.setId(1L);
		productDto.setName("Fortuner");
		
		BrandDto brandDto = new BrandDto();
		brandDto.setId(1L);
		brandDto.setName("Toyota");
		
		productDto.setBrand(brandDto);

		Product product = Mappers.getMapper(ProductMapper.class).dtoToEntity(productDto);

		assertEquals(productDto.getId().longValue(), product.getId().longValue());
		assertEquals(productDto.getName(), product.getName());
	}
}
