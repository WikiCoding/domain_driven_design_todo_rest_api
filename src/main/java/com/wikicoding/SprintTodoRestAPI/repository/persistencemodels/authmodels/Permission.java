package com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    ASSISTANT_READ("management:read"),
    ASSISTANT_UPDATE("management:update"),
    ASSISTANT_CREATE("management:create"),
    ASSISTANT_DELETE("management:delete");

    private final String permission;
}
