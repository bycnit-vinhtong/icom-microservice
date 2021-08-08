package com.icommerce.inventory;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.icommerce.catalog.CatalogServiceTestsApp;
import com.icommerce.inventory.client.InventoryItemDto;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=RANDOM_PORT,  properties = {"eureka.client.enabled=false"},classes = {CatalogServiceTestsApp.class})
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL,
ids = "com.icommerce.inventory:inventory-server:+:stubs:8005",
repositoryRoot = "classpath:m2repo/repository/")
@ActiveProfiles(profiles = "test")
public class ContractRestClientApplicationTest {

	@Test
	public void get_inventory_from_service_contract() {
		// given:
		RestTemplate restTemplate = new RestTemplate();

		// when:
		ResponseEntity<InventoryItemDto> personResponseEntity = restTemplate
				.getForEntity("http://localhost:8005/inventory/1", InventoryItemDto.class);

		// then:
		BDDAssertions.then(personResponseEntity.getStatusCodeValue()).isEqualTo(200);
		BDDAssertions.then(personResponseEntity.getBody().getId()).isEqualTo(1l);
		BDDAssertions.then(personResponseEntity.getBody().getProductCode()).isEqualTo("PD01");
		BDDAssertions.then(personResponseEntity.getBody().getAvailableQuantity()).isEqualTo(10);

	}
}
