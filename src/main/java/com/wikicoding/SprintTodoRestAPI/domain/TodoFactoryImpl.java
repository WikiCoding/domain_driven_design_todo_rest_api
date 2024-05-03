package com.wikicoding.SprintTodoRestAPI.domain;

import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.User;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoComplete;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoDescr;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoId;
import org.springframework.stereotype.Component;

@Component
public class TodoFactoryImpl implements TodoFactory {
    @Override
    public Todo createTodo(TodoId todoId, TodoDescr todoDescr, TodoComplete todoComplete, User user) {
        try {
            return new Todo(todoId, todoDescr, todoComplete, user);
        } catch (Exception e) {
            return null;
        }
    }
}
