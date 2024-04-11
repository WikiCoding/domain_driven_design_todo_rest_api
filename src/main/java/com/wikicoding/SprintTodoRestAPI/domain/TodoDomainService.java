package com.wikicoding.SprintTodoRestAPI.domain;

import com.wikicoding.SprintTodoRestAPI.dto.TodoDto;
import com.wikicoding.SprintTodoRestAPI.exceptions.WrongInputException;
import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.User;
import com.wikicoding.SprintTodoRestAPI.vo.TodoComplete;
import com.wikicoding.SprintTodoRestAPI.vo.TodoDescr;
import com.wikicoding.SprintTodoRestAPI.vo.TodoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoDomainService {
    private final TodoFactoryImpl todoFactory;

    @Autowired
    public TodoDomainService(TodoFactoryImpl todoFactory) {
        this.todoFactory = todoFactory;
    }

    public Todo addTodoDomainService(TodoDto todoDto, User user) {
        if (invalidTodoDtoData(todoDto)) throw new WrongInputException("Bad input data. Fields can't be empty, blank or null");
        int id = 0;
        TodoId todoId = TodoId.builder().id(id).build();
        TodoDescr todoDescr = TodoDescr.builder().descr(todoDto.getTodo()).build();
        TodoComplete todoComplete = TodoComplete.builder().complete(todoDto.isComplete()).build();
        return todoFactory.createTodo(todoId, todoDescr, todoComplete, user);
    }

    public Todo updateTodoDomainService(int todoId, TodoDto todoDto, User user) {
        if (invalidTodoDtoData(todoDto)) throw new WrongInputException("Bad input data. Fields can't be empty, blank or null");
        TodoId todoIdVo = TodoId.builder().id(todoId).build();
        TodoDescr todoDescr = TodoDescr.builder().descr(todoDto.getTodo()).build();
        TodoComplete todoComplete = TodoComplete.builder().complete(todoDto.isComplete()).build();
        return todoFactory.createTodo(todoIdVo, todoDescr, todoComplete, user);
    }

    private boolean invalidTodoDtoData(TodoDto todoDto) {
        return todoDto.getTodo() == null || todoDto.getTodo().trim().isEmpty();
    }
}
