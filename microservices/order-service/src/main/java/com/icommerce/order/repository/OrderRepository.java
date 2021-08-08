package com.icommerce.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.icommerce.order.domain.Order;

@Repository
public interface OrderRepository
		extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
	Optional<Order> findById(Long orderId);
}
