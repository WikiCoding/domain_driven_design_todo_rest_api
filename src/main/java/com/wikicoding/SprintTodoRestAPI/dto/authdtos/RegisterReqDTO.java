package com.wikicoding.SprintTodoRestAPI.dto.authdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReqDTO {
    private String username;
    private String password;
    private String role;
}
