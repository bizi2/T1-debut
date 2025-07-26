package com.authservice.kafka;

import com.authservice.model.Role;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class AuthEvent {
    private final String eventId;
    private final EventType eventType;
    private final String username;
    private final String email;
    private final Set<Role> roles;
    private final Instant timestamp;

    public AuthEvent(EventType eventType, String username, String email, Set<Role> roles) {
        this(UUID.randomUUID().toString(), eventType, username, email, roles, Instant.now());
    }

    public AuthEvent(String eventId, EventType eventType, String username, String email, Set<Role> roles, Instant timestamp) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.timestamp = timestamp;
    }

    public enum EventType {
        USER_REGISTERED,
        USER_LOGGED_IN,
        USER_LOGGED_OUT,
        PASSWORD_CHANGED
    }

    // Геттеры
    public String getEventId() {
        return eventId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    // Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String eventId = UUID.randomUUID().toString();
        private EventType eventType;
        private String username;
        private String email;
        private Set<Role> roles;
        private Instant timestamp = Instant.now();

        public Builder eventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder eventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public Builder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public AuthEvent build() {
            return new AuthEvent(eventId, eventType, username, email, roles, timestamp);
        }
    }

    @Override
    public String toString() {
        return "AuthEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventType=" + eventType +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", timestamp=" + timestamp +
                '}';
    }
}