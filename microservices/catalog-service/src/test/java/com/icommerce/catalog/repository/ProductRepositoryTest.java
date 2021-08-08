package com.icommerce.catalog.repository;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icommerce.catalog.CatalogServiceTestsApp;
import com.icommerce.catalog.domain.Product;



@ExtendWith({ SpringExtension.class })
@SpringBootTest(webEnvironment=RANDOM_PORT, properties = {"eureka.client.enabled=false"},classes = {CatalogServiceTestsApp.class})

public class ProductRepositoryTest {

	@Autowired
	private ProductRepository repository;

	@Test
	void testFindAll() {
		List<Product> applications = repository.findAll();
		Assertions.assertEquals(3, applications.size(), "We should have 3 products in our database");
	}
}
