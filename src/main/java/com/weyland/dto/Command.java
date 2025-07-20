package com.weyland.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public record Command(
        @NotBlank @Size(max = 1000) String description,
        @NotNull Priority priority,
        @NotBlank @Size(max = 100) String author,
        @NotNull @DateTimeFormat(iso = ISO.DATE_TIME) String time
) {}