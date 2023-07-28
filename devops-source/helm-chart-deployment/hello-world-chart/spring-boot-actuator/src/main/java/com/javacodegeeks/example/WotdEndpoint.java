package com.javacodegeeks.example;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WotdEndpoint {
	
	@GetMapping("")
	public String wordOfTheDay() {
		return "Hello Brilliant!";
	}
	
	@GetMapping("/ipaddress")
	public String info(HttpServletRequest request) {
		return request.getRemoteAddr();
	}

}
