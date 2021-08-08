package com.icommerce.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.icommerce.order.domain.Order;
import com.icommerce.order.dto.PlaceOrderRequestDto;
import com.icommerce.order.dto.PlaceOrderResponseDto;


@Mapper(componentModel = "spring")
public interface OrderMapper {
	
	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
	
	 @Mappings({
	      @Mapping(target="amount", source="order.price"),
	      @Mapping(target="orderId", source="order.id")
	    })
	PlaceOrderResponseDto entityToDto(Order order);

	Order dtoToEntity(PlaceOrderRequestDto placeOrderRequestDto);
	
}
