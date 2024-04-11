package com.wikicoding.SprintTodoRestAPI.repository;

import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.TodoModel;
import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoModel, Integer> {
    @Override
    @Transactional
    <S extends TodoModel> S save(S entity);

    List<TodoModel> findByUser(User user);
}
