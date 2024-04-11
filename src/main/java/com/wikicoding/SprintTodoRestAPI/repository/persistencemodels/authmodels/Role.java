package com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wikicoding.SprintTodoRestAPI.repository.persistencemodels.authmodels.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    ASSISTANT_CREATE,
                    ASSISTANT_READ,
                    ASSISTANT_UPDATE,
                    ASSISTANT_DELETE
            )
    ),
    ASSISTANT(
            Set.of(
                    ASSISTANT_CREATE,
                    ASSISTANT_READ,
                    ASSISTANT_UPDATE,
                    ASSISTANT_DELETE
            )
    );

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
