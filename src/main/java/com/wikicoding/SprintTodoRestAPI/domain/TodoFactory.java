package com.wikicoding.SprintTodoRestAPI.domain;

import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.User;
import com.wikicoding.SprintTodoRestAPI.vo.TodoComplete;
import com.wikicoding.SprintTodoRestAPI.vo.TodoDescr;
import com.wikicoding.SprintTodoRestAPI.vo.TodoId;

public interface TodoFactory {
    Todo createTodo(TodoId todoId, TodoDescr todoDescr, TodoComplete todoComplete, User user);
}
