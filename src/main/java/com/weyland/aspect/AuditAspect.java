package com.weyland.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
public class AuditAspect {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public AuditAspect(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @AfterReturning(
            pointcut = "@annotation(weylandWatching)",
            returning = "result"
    )
    public void audit(JoinPoint jp, WeylandWatchingYou weylandWatching, Object result) {
        String auditMessage = String.format(
                "Method: %s, Args: %s, Result: %s",
                jp.getSignature().getName(),
                Arrays.toString(jp.getArgs()),
                result
        );

        if (weylandWatching.logToConsole()) {
            System.out.println("[AUDIT] " + auditMessage);
        }

        if (!weylandWatching.kafkaTopic().isEmpty()) {
            kafkaTemplate.send(weylandWatching.kafkaTopic(), auditMessage);
        }
    }
}