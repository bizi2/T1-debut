package com.weyland.exception;

import com.weyland.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.time.Instant; // Добавьте этот импорт

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommandQueueFullException.class)
    public ResponseEntity<ErrorResponse> handleQueueFull(
            CommandQueueFullException ex,
            WebRequest request
    ) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(
                        400,
                        "Queue overflow",
                        ex.getMessage()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex,
            WebRequest request
    ) {
        return ResponseEntity.internalServerError().body(
                new ErrorResponse(
                        Instant.now().toString(),
                        500,
                        "Internal Server Error",
                        ex.getMessage(),
                        request.getDescription(false)
                ) // Добавлена закрывающая скобка
        );
    }
}