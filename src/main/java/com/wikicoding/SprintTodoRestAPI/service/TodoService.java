package com.wikicoding.SprintTodoRestAPI.service;

import com.wikicoding.SprintTodoRestAPI.domain.Todo;
import com.wikicoding.SprintTodoRestAPI.domain.TodoFactory;
import com.wikicoding.SprintTodoRestAPI.dto.TodoMapper;
import com.wikicoding.SprintTodoRestAPI.dto.TodoResponse;
import com.wikicoding.SprintTodoRestAPI.exceptions.NotFoundException;
import com.wikicoding.SprintTodoRestAPI.repository.TodoRepository;
import com.wikicoding.SprintTodoRestAPI.repository.authrepositories.UserRepository;
import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.TodoModel;
import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.User;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoComplete;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoDescr;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoId;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoService {
    private final UserRepository userRepository;
    private final TodoRepository repository;
    private final TodoFactory todoFactory;
    private final TodoMapper todoMapper;

    public Todo addTodo(TodoId todoId, TodoDescr todoDescr, TodoComplete todoComplete) {
        User user = getUserAuthenticated();

        Todo todo = todoFactory.createTodo(todoId, todoDescr, todoComplete, user);

        TodoModel todoModel = new TodoModel(todo);

        TodoModel saved = repository.save(todoModel);

        return todoMapper.dataModelToDomain(saved);
    }

    private User getUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(authentication.getName());
        if (user.isEmpty()) throw new NotFoundException("User not found");
        return user.get();
    }

    public Iterable<Todo> getTodos() {
        User user = getUserAuthenticated();

        List<TodoModel> todosModels = repository.findByUser(user);

        return new TodoMapper(todoFactory).listDataModelToDomain(todosModels);
    }

    public Todo updateTodo(int todoId, TodoDescr todoDescr, TodoComplete todoComplete) {
        Optional<TodoModel> todoModel = repository.findById(todoId);
        if (todoModel.isEmpty()) throw new NotFoundException("Todo not found");
        TodoModel todoModelToUpdate = todoModel.get();

        User user = getUserAuthenticated();
        TodoId todoIdObj = new TodoId(todoId);

        Todo todo = todoFactory.createTodo(todoIdObj, todoDescr, todoComplete, user);
        todoModelToUpdate.updateTodo(todo);

        TodoModel saved = repository.save(todoModelToUpdate);

        return todoMapper.dataModelToDomain(saved);
    }

    public Todo deleteTodo(int todoId) {
        Optional<TodoModel> todoModel = repository.findById(todoId);
        if (todoModel.isEmpty()) throw new NotFoundException("Todo not found");
        repository.deleteById(todoId);

        return todoMapper.dataModelToDomain(todoModel.get());
    }
}
