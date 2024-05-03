package com.wikicoding.SprintTodoRestAPI.value_objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class TodoDescr {
    @NotBlank
    @NotEmpty
    @NotNull
    private final String descr;
}
