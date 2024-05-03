package com.wikicoding.SprintTodoRestAPI.controller;

import com.wikicoding.SprintTodoRestAPI.domain.Todo;
import com.wikicoding.SprintTodoRestAPI.dto.TodoDto;
import com.wikicoding.SprintTodoRestAPI.dto.TodoMapper;
import com.wikicoding.SprintTodoRestAPI.dto.TodoResponse;
import com.wikicoding.SprintTodoRestAPI.service.TodoService;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoComplete;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoDescr;
import com.wikicoding.SprintTodoRestAPI.value_objects.TodoId;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper todoMapper;

    @GetMapping("todo")
    public ResponseEntity<Iterable<TodoResponse>> getTodos() {
        Iterable<Todo> todoResponseList = todoService.getTodos();

        return ResponseEntity.status(HttpStatus.OK).body(todoMapper.listDomainToResponse(todoResponseList));
    }

    @PostMapping("todo")
    public ResponseEntity<TodoResponse> addTodo(@RequestBody TodoDto todoDto) {
        TodoId todoId = TodoId.builder().id(0).build();
        TodoDescr todoDescr = TodoDescr.builder().descr(todoDto.getTodo()).build();
        TodoComplete todoComplete = TodoComplete.builder().complete(todoDto.isComplete()).build();
        Todo todo = todoService.addTodo(todoId, todoDescr, todoComplete);
        TodoResponse todoResponse = TodoResponse.builder().id(todo.getTodoId().getId())
                .todo(todo.getTodoDescr().getDescr())
                .completed(todo.getTodoComplete().isComplete())
                .userId(todo.getUser().getId()).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(todoResponse);
    }

    @PutMapping("todo/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable int id, @RequestBody TodoDto todoDto) {
        TodoDescr todoDescr = TodoDescr.builder().descr(todoDto.getTodo()).build();
        TodoComplete todoComplete = TodoComplete.builder().complete(todoDto.isComplete()).build();
        Todo todo = todoService.updateTodo(id, todoDescr, todoComplete);

        return ResponseEntity.status(HttpStatus.OK).body(todoMapper.domainToResponse(todo));
    }

    @DeleteMapping("todo/{id}")
    public ResponseEntity<TodoResponse> deleteTodo(@PathVariable int id) {
        Todo todo = todoService.deleteTodo(id);

        return ResponseEntity.status(HttpStatus.OK).body(todoMapper.domainToResponse(todo));
    }
}
