package com.example.webflux.controller;

import com.example.webflux.dto.TodoResponseDto;
import com.example.webflux.service.TestService;
import com.example.webflux.service.TestServiceImpl;
import com.example.webflux.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/test-reactive")
public class TestReactiveController {

    private final TestService testService; 
    
    @Autowired
    public TestReactiveController(TestService testService) {
        this.testService = testService;
    }

     // The server will push messages line by line to your browser - scrolling effect on your browser
    @GetMapping("/test2")
    @Operation(summary = "Test 2")
    public Flux<TodoResponseDto> test2() {
        return testService.test2();
    }
}
