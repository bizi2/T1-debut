package com.authservice.dto;

import com.authservice.model.Role;
import java.time.LocalDateTime;
import java.util.Set;

public class UserResponse {
    public final Long id;
    public final String username;
    public final String email;
    public final Set<Role> roles;
    public final LocalDateTime createdAt;
    public final LocalDateTime updatedAt;
    public final boolean active;

    public UserResponse(Long id, String username, String email, Set<Role> roles,
                        LocalDateTime createdAt, LocalDateTime updatedAt, boolean active) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.active = active;
    }
}