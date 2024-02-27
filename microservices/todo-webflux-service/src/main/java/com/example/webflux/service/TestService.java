package com.example.webflux.service;

import com.example.webflux.dto.TodoResponseDto;

import reactor.core.publisher.Flux;

public interface TestService {
    public Flux<TodoResponseDto> test2();
}
