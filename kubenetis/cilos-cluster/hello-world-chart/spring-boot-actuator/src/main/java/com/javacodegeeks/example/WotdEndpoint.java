package com.javacodegeeks.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WotdEndpoint {
	
	@GetMapping("")
	public String wordOfTheDay() {
		return "Hello Brilliant!";
	}

}
