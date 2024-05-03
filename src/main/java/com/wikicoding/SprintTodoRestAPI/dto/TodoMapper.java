package com.wikicoding.SprintTodoRestAPI.dto;

import com.wikicoding.SprintTodoRestAPI.domain.Todo;
import com.wikicoding.SprintTodoRestAPI.domain.TodoFactory;
import com.wikicoding.SprintTodoRestAPI.domain.TodoFactoryImpl;
import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.TodoModel;
import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.User;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoComplete;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoDescr;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoMapper {
    private final TodoFactory todoFactory;

    public TodoMapper(TodoFactory todoFactory) {
        this.todoFactory = todoFactory;
    }

    public Todo dataModelToDomain(TodoModel todoModel) {
        return buildTodoObject(todoModel);
    }

    public Iterable<Todo> listDataModelToDomain(Iterable<TodoModel> todos) {
        List<Todo> todosRes = new ArrayList<>();

        for (TodoModel todoModel : todos) {
            Todo todoRes = buildTodoObject(todoModel);
            todosRes.add(todoRes);
        }

        return todosRes;
    }

    public Iterable<TodoResponse> listDomainToResponse(Iterable<Todo> todos) {
        List<TodoResponse> todoResponses = new ArrayList<>();

        for (Todo todo : todos) {
            TodoResponse todoResponse = domainToResponse(todo);

            todoResponses.add(todoResponse);
        }

        return todoResponses;
    }

    public TodoResponse domainToResponse(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getTodoId().getId())
                .todo(todo.getTodoDescr().getDescr())
                .completed(todo.getTodoComplete().isComplete())
                .userId(todo.getUser().getId())
                .build();
    }

    private Todo buildTodoObject(TodoModel todoModel) {
        TodoId todoId = new TodoId(todoModel.getModelId());
        TodoDescr todoDescr = new TodoDescr(todoModel.getTodo());
        TodoComplete todoComplete = new TodoComplete(todoModel.isCompleted());
        User user = new User(todoModel.getUser().getId(), todoModel.getUser().getUsername(),
                todoModel.getUser().getPassword(), todoModel.getUser().getCreatedAt(),
                todoModel.getUser().getRole());
        Todo todoRes = todoFactory.createTodo(todoId, todoDescr, todoComplete, user);
        return todoRes;
    }
}
