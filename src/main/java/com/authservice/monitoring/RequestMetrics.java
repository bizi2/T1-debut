package com.authservice.monitoring;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestMetrics implements HandlerInterceptor {

    private final Counter apiRequests;

    public RequestMetrics(MeterRegistry registry) {
        this.apiRequests = Counter.builder("api.requests")
                .description("Total API requests count")
                .register(registry);
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        apiRequests.increment();
        return true;
    }
}