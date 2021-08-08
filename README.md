# Icommerce


## Architecture Diagrams
The POC application is implemented basing on micro service pattern using Spring Cloud and Rabbit MQ as the message broker for asynchrony communication between services

Each back-end service is a standard Maven, Spring Boot application. The diagram below will explain the infrastructure and components.

![architect local](https://github.com/ben31052012/images/blob/master/archi_local.jpg)


## Setup environment
### Prerequisite
Your local machine must install libraries below 
1.	Java JDK 8 or above
2.	Maven 3.x
3.	Docker 

### Build the application using maven
`mvn clean install`

The command will compile all depending modules and launch unit test,  You should see the screen below when the installation finished.
![build result](https://github.com/ben31052012/images/blob/master/build_result.jpg)

### Run the application with Docker on your local machine

`docker-compose up -d --build`

There are many ways to run the application on your local machine, running the application with Docker allowing to play around with the application quickly. 
When finishing, you will see below screen, after that you should wait few minutes more to let all the module finish deployment
![docker up](https://github.com/ben31052012/images/blob/master/docker_up.png)

### Development

`docker-compose down`

`docker-compose up -d rabbitmq service-discovery api-gateway`

This docker command line will start the infrastructure components rabbitmq, Service discovery and gateway.


Navigate to microservices  folder, there are 3 back-end service applications catalog-service, audit-service and inventory-service which  are standard Maven, Spring Boot applications.
You can start the application using maven command line

`mvn spring-boot:run`

Or you could import all Maven projects to your IDE (Eclipse or others IDE). 
It needs to configure your IDE in order to allow to work with Lombok and Structure Map library

Lombok: 
https://www.baeldung.com/lombok-ide

Mapstruct: 
https://mapstruct.org/documentation/ide-support/

For detail port and configuration, check in each application yaml configuration file src/main/resources/application.yml

Each application is also integrated Swagger for testing purpose, we could explorer all available API via url http://localhost:port/swagger-ui.html

#### Source code folder structure

![folder](https://github.com/ben31052012/images/blob/master/folder.JPG)

1. pom.xml file 
<br>Includes all related projects, it helps to compile and execute unit test all project in one shot
2. sping-cloud folder
<br>Including gateway and service-discovery basing on Spring Boot and Spring cloud
- Gateway uses Spring cloud Zuul
- service-discovery uses Spring cloud Eureka server
3. microservice folder
<br>Including 3 main back-end services audit-service, catalog-service and inventory-service
Each service uses the same folder strucutre and libraries
- Spring boot
- Spring web
- Spring data JPA
- Spring Eureka client
- Spring bus amqp and stream rabbit
- spring boot test
- Lombok
- H2 database
- Mapstruct
- Swagger2
- Another utilites and test libraries 

There is a data sql file in src/main/resources folder help to insert some dummy data for testing purpose.

## Available APIs & Demonstration

### Catalog service

| Name | Description |
| ------ | ------ |
| URL | http://localhost:7000/catalogapi/product/{id} |
| Description | get product detail by id |
| Method | GET |
| Input | id: integer |
| Output | Product detail object in JSON format |

Example
URL: http://localhost:7000/catalogapi/product/1

Output
![docker up](https://github.com/ben31052012/images/blob/master/output_product_view.jpg)



| Name | Description |
| ------ | ------ |
| URL | http://localhost:7000/catalogapi/product/find |
| Description | Search product by keyword and filter  by Category, Brand, Color or Price   |
| Method | POST |
| Input | JSON format <br>{<br>"filters": {"keyword":”value”, "keyword": “value”, … }, <br>"keyWord": "bracket",<br>"page": 0,<br>"size": 0,<br>"sortField": "name",<br>"sortOrder": 0<br>}<br>**filters**: list of keyword and value<br><br>*keyword*: <br>(category, brand, color, pricelow,pricehight)<br><br>*value*: <br>with keyword: category, brand,  pricelow, pricehight, value must be a number<br>with color keyword, value must be a text (red, green..)<br><br>**page, size**:number<br>**sortfield**: name, price<br> **sortorder**:0(desc), 1(asc)|
| Output | List Product detail and pagination infomation  in JSON format |

Example

URL: http://localhost:7000/catalogapi/product/find

Input:

```
{
  "filters": {"brand":"1", "category": "1", "pricehight":"60", "pricelow":"30.5","color":"read"},
  "keyWord": "bracket",
  "page": 0,
  "size": 0,
  "sortField": "name",
  "sortOrder": 0
}
```
Output

![find product](https://github.com/ben31052012/images/blob/master/output_find_product.jpg)


### Inventory service
| Name | Description |
| ------ | ------ |
| URL | http://localhost:7000/inventoryapi/inventory/{productId} |
| Description | get quantity for a product  |
| Method | GET |
| Output | Number |

Example:
URL: http://localhost:7000/inventoryapi/inventory/1

### Audit service
| Name | Description |
| ------ | ------ |
| URL | http://localhost:7000/auditapi/audit |
| Description | get all log recorded when user view product detail or search product  |
| Method | GET |
| Output | logs in JSON format |

Example:
URL: http://localhost:7000/auditapi/audit

## Demonstrate communication between services

![communicate](https://github.com/ben31052012/images/blob/master/service_com.jpg)

1.	Catalog Service
<br> Get a product detail with id = 1 <br>
URL: http://localhost:7000/catalogapi/product/1 <br>
Catalog service doesn’t have quantity data of products then it needs to send a synchronized request to Inventory Service to get quantity of a product
It also sends an asynchronous request to Audit service to ask logging the action for auditing

2.	Inventory service  <br>
Get the quantity of the product id = 1 and verify the respone of Catalog Service  <br>
URL: http://localhost:7000/inventoryapi/inventory/1

3.	Audit service
<br>Check whether actions are recorded in the log database whenever calling to Catalog Service  <br>
http://localhost:7000/auditapi/audit


## Database schema

### Catalog-service

![catalog db](https://github.com/ben31052012/images/blob/master/db_cata.jpg)

### Inventory-service

![inventory db](https://github.com/ben31052012/images/blob/master/inventory_db.jpg)

### Audit-service

![audit db](https://github.com/ben31052012/images/blob/master/audit_db.jpg)


## Deploy with Kubernetes cluster on Azure cloud



![Image of Yaktocat](https://github.com/ben31052012/images/blob/master/cloud_ar.jpg)

