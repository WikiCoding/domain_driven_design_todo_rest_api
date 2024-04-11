package com.wikicoding.SprintTodoRestAPI;

import com.wikicoding.SprintTodoRestAPI.dto.authdtos.AuthResponseDTO;
import com.wikicoding.SprintTodoRestAPI.dto.authdtos.RegisterReqDTO;
import com.wikicoding.SprintTodoRestAPI.service.authservice.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SprintTodoRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprintTodoRestApiApplication.class, args);
    }

    // Dev purposes only
    @Bean
    public CommandLineRunner commandLineRunner(AuthenticationService authenticationService) {
        return args -> {
            RegisterReqDTO registerReqDTO = new RegisterReqDTO("user1", "123", "ADMIN");
            AuthResponseDTO authResponseDTO = authenticationService.register(registerReqDTO);
            System.out.println(authResponseDTO.getAccessToken());
        };
    }
}
