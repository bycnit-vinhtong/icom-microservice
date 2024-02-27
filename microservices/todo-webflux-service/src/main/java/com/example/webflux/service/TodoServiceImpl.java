package com.example.webflux.service;

import com.example.webflux.domain.Todo;
import com.example.webflux.dto.TodoResponseDto;
import com.example.webflux.exception.TodoNotFoundException;
import com.example.webflux.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Mono<Todo> findTodoById(int id) {
        Mono<Todo> todo = todoRepository.findById(id).switchIfEmpty(Mono.error(new TodoNotFoundException(String.format("Todo not found. ID: %s", id))));
        return todo;
    }    

    @Override
    public Mono<TodoResponseDto> findTodoDtoById(int id) {
        return todoRepository.findById(id)
                .switchIfEmpty(Mono.error(new TodoNotFoundException(String.format("Todo not found. ID: %s", id))))
                .map(this::convertToTodoDto);
    }

    private TodoResponseDto convertToTodoDto(Todo todo) {
        return TodoResponseDto.builder()
                .id(todo.getId())
                .titles(todo.getTitle())
                .descriptions(todo.getDescription())
                .build();
                
    }

    @Override
    public Mono<Todo> searchTodoByTitle(String title) {
        return todoRepository.findTodoByTitle(title);
    }

    @Override
    public Flux<Todo> findAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Flux<TodoResponseDto> findAllTodoDtos() {
        return todoRepository.findAll()
            .map(this::convertToTodoDto);
    }

    @Override
    public Mono<Todo> saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Mono<Void> deleteTodoById(int id) {
        return todoRepository.deleteById(id);
    }
}
