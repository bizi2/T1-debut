package com.weyland.service;

import io.micrometer.core.instrument.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MetricsService {
    private final MeterRegistry meterRegistry;
    private final Map<String, Counter> authorCounters = new ConcurrentHashMap<>();

    public MetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }
    public MeterRegistry getMeterRegistry() {
        return this.meterRegistry;
    }
    // Регистрация выполнения команды
    public void recordCommandExecution(String author) {
        authorCounters.computeIfAbsent(author,
                a -> Counter.builder("commands.executed")
                        .tag("author", a)
                        .register(meterRegistry)
        ).increment();
    }

    // Получение текущего размера очереди
    public int getCurrentQueueSize() {
        Gauge gauge = meterRegistry.find("queue.size").gauge();
        return gauge != null ? (int)gauge.value() : 0;
    }

    // Получение статистики по авторам
    public Map<String, Double> getAuthorStatistics() {
        Map<String, Double> stats = new ConcurrentHashMap<>();
        meterRegistry.find("commands.executed").counters()
                .forEach(c -> stats.put(
                        c.getId().getTag("author"),
                        c.count()
                ));
        return stats;
    }
}