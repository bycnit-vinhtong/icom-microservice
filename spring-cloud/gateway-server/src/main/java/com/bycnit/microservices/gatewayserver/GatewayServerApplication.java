package com.bycnit.microservices.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

	/*@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("path_route", r -> r.path("/product/**").and()
					.uri("http://catalog-service:80"))
				.route("path_route", r -> r.path("/inventory-service/**")
					//.filters(f -> f.addResponseHeader("Cache-Control", "max-age=300"))					
					.uri("http://inventory-service:80"))
					.build();
	}*/

}
