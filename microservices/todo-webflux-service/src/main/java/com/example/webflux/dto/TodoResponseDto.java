package com.example.webflux.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoResponseDto {
    private int id;

    private String titles;

    private String descriptions;
}
