package com.icommerce.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.icommerce.inventory.domain.InventoryItem;

@Repository
public interface InventoryItemRepository
		extends JpaRepository<InventoryItem, Long>, JpaSpecificationExecutor<InventoryItem> {
	Optional<InventoryItem> findByProductId(Long productId);
}
