package com.wikicoding.SprintTodoRestAPI.domain;

import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.User;
import com.wikicoding.SprintTodoRestAPI.vo.TodoComplete;
import com.wikicoding.SprintTodoRestAPI.vo.TodoDescr;
import com.wikicoding.SprintTodoRestAPI.vo.TodoId;
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
