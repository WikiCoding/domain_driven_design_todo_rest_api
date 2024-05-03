package com.wikicoding.SprintTodoRestAPI.value_objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class TodoId {
    private final int id;
}
