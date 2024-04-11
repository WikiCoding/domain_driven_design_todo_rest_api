package com.wikicoding.SprintTodoRestAPI.service;

import com.wikicoding.SprintTodoRestAPI.domain.Todo;
import com.wikicoding.SprintTodoRestAPI.domain.TodoDomainService;
import com.wikicoding.SprintTodoRestAPI.dto.TodoDto;
import com.wikicoding.SprintTodoRestAPI.dto.TodoResponse;
import com.wikicoding.SprintTodoRestAPI.exceptions.NotFoundException;
import com.wikicoding.SprintTodoRestAPI.repository.TodoRepository;
import com.wikicoding.SprintTodoRestAPI.repository.authrepositories.UserRepository;
import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.TodoModel;
import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.User;
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
    private final TodoDomainService todoDomainService;

    public TodoResponse addTodo(TodoDto todoDto) {
        if (todoDto == null) throw new NullPointerException("This never happens, lol");

        User user = getUserAuthenticated();

        Todo todo = todoDomainService.addTodoDomainService(todoDto, user);
        TodoModel todoModel = new TodoModel(todo);

        TodoModel saved = repository.save(todoModel);
        return TodoResponse.builder().id(saved.getModelId()).todo(saved.getTodo()).completed(saved.isCompleted())
                .userId(saved.getUser().getId()).version(saved.getVersion()).build();
    }

    private User getUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(authentication.getName());
        if (user.isEmpty()) throw new NotFoundException("User not found");
        return user.get();
    }

    public Iterable<TodoResponse> getTodos() {
        User user = getUserAuthenticated();

        List<TodoModel> todos = repository.findByUser(user);
        List<TodoResponse> todoResponseList = new ArrayList<>();
        todos.forEach(todo -> {
            TodoResponse todoResponse = TodoResponse.builder()
                    .id(todo.getModelId())
                    .todo(todo.getTodo())
                    .completed(todo.isCompleted())
                    .userId(user.getId())
                    .version(todo.getVersion())
                    .build();
            todoResponseList.add(todoResponse);
        });

        return todoResponseList;
    }

    public TodoResponse updateTodo(int todoId, TodoDto todoDto) {
        if (todoDto == null) throw new NullPointerException("This never happens, lol");
        Optional<TodoModel> todoModel = repository.findById(todoId);
        if (todoModel.isEmpty()) throw new NotFoundException("Todo not found");
        TodoModel todoModelToUpdate = todoModel.get();

        User user = getUserAuthenticated();

        Todo todo = todoDomainService.updateTodoDomainService(todoId, todoDto, user);
        TodoModel updated = buildUpdatedTodoModel(todoModelToUpdate, todo);

        TodoModel saved = repository.save(updated);

        return TodoResponse.builder().id(saved.getModelId()).todo(saved.getTodo()).completed(saved.isCompleted())
                .userId(user.getId()).version(todoModel.get().getVersion()).build();
    }

    public TodoResponse deleteTodo(int todoId) {
        Optional<TodoModel> todoModel = repository.findById(todoId);
        if (todoModel.isEmpty()) throw new NotFoundException("Todo not found");
        repository.deleteById(todoId);
        return TodoResponse.builder()
                .id(todoModel.get().getModelId())
                .todo(todoModel.get().getTodo())
                .completed(todoModel.get().isCompleted())
                .version(todoModel.get().getVersion())
                .build();
    }

    private TodoModel buildUpdatedTodoModel(TodoModel todoModelToUpdate, Todo todo) {
        todoModelToUpdate.setTodo(todo.getTodoDescr().getDescr());
        int currentVersion = todoModelToUpdate.getVersion();
        todoModelToUpdate.setVersion(currentVersion + 1);
        todoModelToUpdate.setCompleted(todo.getTodoComplete().isComplete());
        return todoModelToUpdate;
    }
}
