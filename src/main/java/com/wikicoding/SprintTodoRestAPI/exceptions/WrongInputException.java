package com.wikicoding.SprintTodoRestAPI.exceptions;

public class WrongInputException extends RuntimeException {
    public WrongInputException(String message) {
        super(message);
    }
}
