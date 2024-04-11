package com.wikicoding.SprintTodoRestAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TodoResponse {
    private final int id;
    private final String todo;
    private final boolean completed;
    private final int userId;
    private final int version;
}
