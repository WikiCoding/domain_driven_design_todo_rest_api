package com.wikicoding.SprintTodoRestAPI.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDto {
    private String todo;
    private boolean complete;
}
