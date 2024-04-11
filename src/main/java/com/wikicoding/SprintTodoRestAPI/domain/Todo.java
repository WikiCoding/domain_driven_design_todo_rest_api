package com.wikicoding.SprintTodoRestAPI.domain;

import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.User;
import com.wikicoding.SprintTodoRestAPI.vo.TodoComplete;
import com.wikicoding.SprintTodoRestAPI.vo.TodoDescr;
import com.wikicoding.SprintTodoRestAPI.vo.TodoId;
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
