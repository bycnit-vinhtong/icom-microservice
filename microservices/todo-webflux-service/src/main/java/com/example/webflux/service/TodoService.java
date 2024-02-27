package com.example.webflux.service;

import com.example.webflux.domain.Todo;
import com.example.webflux.dto.TodoResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface TodoService {
    Mono<Todo> findTodoById(int id);
    Mono<TodoResponseDto> findTodoDtoById(int id);
    Mono<Todo> searchTodoByTitle(String title);
    Flux<Todo> findAllTodos();
    Flux<TodoResponseDto> findAllTodoDtos();
    Mono<Todo> saveTodo(Todo todo);
    Mono<Void> deleteTodoById(int id);
}
