package com.icommerce.inventory.service.client;

import com.icommerce.inventory.dto.ProductInfoResponseDto;

public interface CatalogClientService {
    public ProductInfoResponseDto getProductById(Long productId);
}
