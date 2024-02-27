package com.example.webflux.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.webflux.dto.TodoResponseDto;

import java.time.Duration;
import reactor.core.publisher.Flux;

@Service
public class TestServiceImpl implements TestService{

    @Override
    public Flux<TodoResponseDto> test2() {
        Flux<TodoResponseDto> todoFlux = Flux
            .interval(Duration.ofSeconds(1))
            .map(i -> new TodoResponseDto(1, "Test 1", "Test 1"));
        return todoFlux;
    }
    
}
