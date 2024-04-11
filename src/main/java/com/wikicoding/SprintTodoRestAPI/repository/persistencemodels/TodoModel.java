package com.wikicoding.SprintTodoRestAPI.repository.persistencemodels;

import com.wikicoding.SprintTodoRestAPI.domain.Todo;
import com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todo")
@NoArgsConstructor
@Data
public class TodoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int modelId;
    private String todo;
    private boolean completed = false;

    @Version
    @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
    private int version;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public TodoModel(Todo todo) {
        this.modelId = todo.getTodoId().getId();
        this.todo = todo.getTodoDescr().getDescr();
        this.completed = todo.getTodoComplete().isComplete();
        this.user = todo.getUser();
    }
}
