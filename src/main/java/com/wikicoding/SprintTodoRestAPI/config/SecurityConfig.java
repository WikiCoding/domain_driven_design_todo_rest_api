package com.wikicoding.SprintTodoRestAPI.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.Role.ADMIN;
import static com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.Role.ASSISTANT;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(req -> req.anyRequest().permitAll());
        String BASE_PATH = "/todo";
        String AUTH_BASE_PATH = "/auth";

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(AUTH_BASE_PATH +"/**").permitAll()
                        .requestMatchers(HttpMethod.GET, BASE_PATH).hasAnyRole(ADMIN.name())
                        .requestMatchers(HttpMethod.POST, BASE_PATH).hasAnyRole(ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, BASE_PATH + "={id}").hasAnyRole(ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, BASE_PATH + "**").hasAnyRole(ADMIN.name())
                        .requestMatchers(AUTH_BASE_PATH +"/**").permitAll()
                        .requestMatchers(HttpMethod.GET, BASE_PATH).hasAnyRole(ASSISTANT.name()));

        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(c -> c.addLogoutHandler(logoutHandler).logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));

        return http.build();
    }
}
