package com.authservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuthEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(AuthEventConsumer.class);

    @KafkaListener(
            topics = "auth-events",
            groupId = "auth-service-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(AuthEvent event) {
        try {
            log.info("Consumed auth event: {}", event);

            // Здесь можно добавить логику обработки событий:
            // - Аналитика
            // - Уведомления
            // - Аудит действий

        } catch (Exception e) {
            log.error("Error processing auth event: {}", event, e);
        }
    }
}