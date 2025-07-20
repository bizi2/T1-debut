package com.weyland.dto;

import java.time.Instant;

public record ErrorResponse(
        String timestamp,  // Время возникновения ошибки
        int status,       // HTTP-статус код
        String error,     // Тип ошибки (например, "Bad Request")
        String message,   // Детальное сообщение
        String path       // URL, на котором произошла ошибка (опционально)
) {
    public ErrorResponse(int status, String error, String message) {
        this(Instant.now().toString(), status, error, message, null);
    }
}