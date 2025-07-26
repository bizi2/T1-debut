package com.authservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthEventProducer {

    private static final Logger log = LoggerFactory.getLogger(AuthEventProducer.class);
    private final KafkaTemplate<String, AuthEvent> kafkaTemplate;

    public AuthEventProducer(KafkaTemplate<String, AuthEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAuthEvent(AuthEvent event) {
        try {
            kafkaTemplate.send("auth-events", event.getUsername(), event);
            log.info("Sent auth event: {}", event);
        } catch (Exception e) {
            log.error("Failed to send auth event: {}", event, e);
        }
    }
}