package com.wikicoding.SprintTodoRestAPI.dto.authdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {
  private String username;
  String password;
}
