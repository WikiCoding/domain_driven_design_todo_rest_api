package com.wikicoding.SprintTodoRestAPI.controller;

import com.wikicoding.SprintTodoRestAPI.dto.TodoDto;
import com.wikicoding.SprintTodoRestAPI.dto.TodoResponse;
import com.wikicoding.SprintTodoRestAPI.exceptions.WrongInputException;
import com.wikicoding.SprintTodoRestAPI.service.TodoService;
import com.wikicoding.SprintTodoRestAPI.vo.TodoComplete;
import com.wikicoding.SprintTodoRestAPI.vo.TodoDescr;
import com.wikicoding.SprintTodoRestAPI.vo.TodoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo")
    public ResponseEntity<Iterable<TodoResponse>> getTodos() {
        Iterable<TodoResponse> todoResponseList = todoService.getTodos();
        return ResponseEntity.status(HttpStatus.OK).body(todoResponseList);
    }

    @PostMapping("/todo")
    public ResponseEntity<TodoResponse> addTodo(@RequestBody TodoDto todoDto) {
        TodoId todoId = TodoId.builder().id(0).build();
        TodoDescr todoDescr = TodoDescr.builder().descr(todoDto.getTodo()).build();
        TodoComplete todoComplete = TodoComplete.builder().complete(todoDto.isComplete()).build();
        TodoResponse todoResponse = todoService.addTodo(todoId, todoDescr, todoComplete);
        return ResponseEntity.status(HttpStatus.CREATED).body(todoResponse);
    }

    @PutMapping("/todo={id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable int id, @RequestBody TodoDto todoDto) {
        TodoDescr todoDescr = TodoDescr.builder().descr(todoDto.getTodo()).build();
        TodoComplete todoComplete = TodoComplete.builder().complete(todoDto.isComplete()).build();
        TodoResponse todoResponse = todoService.updateTodo(id, todoDescr, todoComplete);
        return ResponseEntity.status(HttpStatus.OK).body(todoResponse);
    }

    @DeleteMapping("/todo={id}")
    public ResponseEntity<TodoResponse> deleteTodo(@PathVariable int id) {
        TodoResponse todoResponse = todoService.deleteTodo(id);
        return ResponseEntity.status(HttpStatus.OK).body(todoResponse);
    }
}
