package com.wikicoding.SprintTodoRestAPI.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class TodoComplete {
    private final boolean complete;
}
