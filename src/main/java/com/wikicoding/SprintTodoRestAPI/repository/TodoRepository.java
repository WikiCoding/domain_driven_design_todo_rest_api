package com.wikicoding.SprintTodoRestAPI.repository;

import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.TodoModel;
import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoModel, Integer> {
    List<TodoModel> findByUser(User user);
}
