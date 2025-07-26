package com.authservice.service;

import com.authservice.kafka.AuthEvent;
import com.authservice.kafka.AuthEventProducer;
import com.authservice.model.User;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventService {

    private final AuthEventProducer authEventProducer;

    public KafkaEventService(AuthEventProducer authEventProducer) {
        this.authEventProducer = authEventProducer;
    }

    public void sendUserRegisteredEvent(User user) {
        AuthEvent event = AuthEvent.builder()
                .eventType(AuthEvent.EventType.USER_REGISTERED)
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();

        authEventProducer.sendAuthEvent(event);
    }

    public void sendUserLoggedInEvent(User user) {
        AuthEvent event = AuthEvent.builder()
                .eventType(AuthEvent.EventType.USER_LOGGED_IN)
                .username(user.getUsername())
                .build();

        authEventProducer.sendAuthEvent(event);
    }
}