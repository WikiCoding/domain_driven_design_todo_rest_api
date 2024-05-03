package com.wikicoding.SprintTodoRestAPI.domain;

import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.User;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoComplete;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoDescr;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class Todo {
    @Setter
    private TodoId todoId;
    private final TodoDescr todoDescr;
    private TodoComplete todoComplete;
    private User user;
}
