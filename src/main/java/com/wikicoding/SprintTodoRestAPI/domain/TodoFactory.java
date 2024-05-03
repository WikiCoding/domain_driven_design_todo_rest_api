package com.wikicoding.SprintTodoRestAPI.domain;

import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.User;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoComplete;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoDescr;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoId;

public interface TodoFactory {
    Todo createTodo(TodoId todoId, TodoDescr todoDescr, TodoComplete todoComplete, User user);
}
