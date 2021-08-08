import org.springframework.cloud.contract.spec.Contract

Contract.make {
  description "should return Inventory by id=1"

  request {
    url "/inventory/1"
    method GET()
  }

  response {
    status OK()
    headers {
      contentType applicationJson()
    }
    body (
      id: 1,
      productCode: "PD01",
      productId: 1,
	  availableQuantity: 10
    )
  }
}