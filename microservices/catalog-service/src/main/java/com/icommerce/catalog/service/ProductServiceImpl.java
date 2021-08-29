package com.icommerce.catalog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icommerce.catalog.constants.ProductField;
import com.icommerce.catalog.domain.Product;
import com.icommerce.catalog.dto.Event;
import com.icommerce.catalog.dto.PageDto;
import com.icommerce.catalog.dto.ProductDto;
import com.icommerce.catalog.dto.SearchCriteria;
import com.icommerce.catalog.exception.NotFoundException;
import com.icommerce.catalog.mapper.ProductMapper;
import com.icommerce.catalog.repository.ProductRepository;
import com.icommerce.catalog.repository.specifications.ProductSpecification;

@Service
@EnableBinding(ProductAuditChanel.class)
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductMapper productMapper;

	@Autowired
	ProductAuditChanel productAuditChanel;
	
	@Autowired
	ProductInventoryService productInventoryService;

	@Value("${app.defaultPageSize}")
	private int defaultPageSize;



	@Value("${app.inventory-service.host}")
	private String inventoryServiceUri;

	@Transactional
	@Override
	public ProductDto createProduct(ProductDto productDto) {
		Product product = productMapper.dtoToEntity(productDto);
		product = productRepository.save(product);
		return productMapper.entityToDto(product);
	}

	@Transactional
	@Override
	public ProductDto updateProduct(ProductDto productDto) {

		final Optional<Product> product = productRepository.findById(productDto.getId());
		if (product.isPresent()) {
			Product productResult = productMapper.dtoToEntity(productDto);
			productResult = productRepository.save(productResult);
			return productMapper.entityToDto(productResult);
		} else {
			throw new NotFoundException(Product.class, productDto.getId());
		}
	}

	@Transactional(readOnly = true)
	@Override
	public ProductDto getProduct(long productId) {
	    logger.info("get product details - process started");
		logSearchCriteria(null, productId, Event.Type.VIEW);
		final Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) {
			ProductDto  productDto = productMapper.entityToDto(product.get());
			productDto.setQuantity(productInventoryService.getInventoryUsingRestTemplate(productDto.getId()));
			logger.info("get product details - product found");
			return productDto;
		} else {
		    logger.info("get product details - product not found");
			throw new NotFoundException(Product.class, productId);
		}
	}

	@Transactional
	@Override
	public void deleteProduct(long productId) {
		final Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) {
			productRepository.delete(product.get());
		} else {
			throw new NotFoundException(Product.class, productId);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public PageDto<ProductDto> findProductsByCriterias(SearchCriteria searchCriteria) {
		//logSearchCriteria(searchCriteria, null, Event.Type.SEARCH);
		if (StringUtils.isEmpty(searchCriteria.getSortField())) {
			searchCriteria.setSortField(ProductField.NAME.label);
		}
		Sort sort = Sort
				.by(new Sort.Order(searchCriteria.getSortOrder() == 1 ? Sort.Direction.ASC : Sort.Direction.DESC,
						searchCriteria.getSortField()));

		int pageSize = searchCriteria.getSize();
		if (pageSize <= 0) {
			pageSize = defaultPageSize;
		}

		Pageable pageable = PageRequest.of(searchCriteria.getPage(), pageSize, sort);

		ProductSpecification searchSpecification = new ProductSpecification(searchCriteria);

		Page<Product> page = productRepository.findAll(searchSpecification, pageable);

		List<ProductDto> productDtoList = new ArrayList<>();
		for (Product product : page.getContent()) {
			ProductDto productDto = productMapper.entityToDto(product);
			productDto.setQuantity(productInventoryService.getInventoryUsingRestTemplate(productDto.getId()));
			productDtoList.add(productDto);
		}
		return new PageDto<>(productDtoList, page);
	}

	//Demo ASYNC with Rappid MQ
	private void logSearchCriteria(SearchCriteria criteria, Long productId, Event.Type event) {
		logger.info("Log search data...{}", criteria);
		productAuditChanel.outputAudit().send(MessageBuilder.withPayload(new Event(event, productId, criteria)).build());
	}

	

}
