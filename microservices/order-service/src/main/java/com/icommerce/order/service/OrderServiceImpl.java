package com.icommerce.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icommerce.order.asynchro.source.OrderPlacedEventSource;
import com.icommerce.order.constants.OrderStatus;
import com.icommerce.order.domain.Order;
import com.icommerce.order.dto.OrchestratorRequestDTO;
import com.icommerce.order.dto.PlaceOrderRequestDto;
import com.icommerce.order.dto.PlaceOrderResponseDto;
import com.icommerce.order.mapper.OrderMapper;
import com.icommerce.order.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
    public static Map<Long, Double> PRODUCT_PRICE;
    static {
    	PRODUCT_PRICE = new HashMap<>();
    	PRODUCT_PRICE.put(1L, 100d);
    	PRODUCT_PRICE.put(2L, 200d);
    	PRODUCT_PRICE.put(3L, 300d);
    }

	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	OrderPlacedEventSource orderPlacedEventSource;
	
	@Autowired
	OrderMapper orderMapper;

	@Override
	public PlaceOrderResponseDto createOrder(PlaceOrderRequestDto placeOrderRequestDto) {
		Order order = orderMapper.dtoToEntity(placeOrderRequestDto);
		order.setStatus(OrderStatus.ORDER_CREATED);
		order.setPrice(PRODUCT_PRICE.get(order.getProductId()));
		order = orderRepo.save(order);
		
		logger.info("Going to place orderPlacedEvent for order : {} ", order);
		
		//publish order event to Saga Orchectrator
		orderPlacedEventSource.publishOrderEvent(getOrchestratorRequestDTO(order,placeOrderRequestDto.getQuantity()));

		return orderMapper.entityToDto(order);
	}

	@Override
	public void compensateOrder(Long orderId) {
		/* delete order record for given order id from database */

		/* publish OoderNotProcessedEvent */

	}

	@Override
	public List<PlaceOrderResponseDto> getAll() {
		return orderRepo.findAll()
				.stream()
				.map(order -> orderMapper.entityToDto(order))
				.collect(Collectors.toList());
	}
	
	public OrchestratorRequestDTO getOrchestratorRequestDTO(Order order, Long quanity){
        OrchestratorRequestDTO orchectratorRequestDTO = new OrchestratorRequestDTO();
        orchectratorRequestDTO.setUserId(order.getUserId());
        Double amount = (quanity != null &&  quanity > 0) ?  order.getPrice() * quanity : order.getPrice(); 
        orchectratorRequestDTO.setAmount(amount);
        orchectratorRequestDTO.setOrderId(order.getId());
        orchectratorRequestDTO.setProductId(order.getProductId());
        orchectratorRequestDTO.setAction(OrchestratorRequestDTO.OrderAction.ORDERPLACED);
        return orchectratorRequestDTO;
    }

}
