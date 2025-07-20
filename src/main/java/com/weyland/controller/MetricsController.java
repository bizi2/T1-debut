package com.weyland.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {
    private final MeterRegistry meterRegistry;
    private final Map<String, Counter> authorCommandCounters = new ConcurrentHashMap<>();

    public MetricsController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    /**
     * Получение текущей загрузки андроида (кол-во задач в очереди)
     */
    @GetMapping("/queue-size")
    public int getQueueSize() {
        Gauge gauge = meterRegistry.find("queue.size").gauge();
        return gauge != null ? (int)gauge.value() : 0;
    }

    /**
     * Получение кол-ва выполненных команд по авторам
     */
    @GetMapping("/commands-by-author")
    public Map<String, Double> getCommandsByAuthor() {
        Map<String, Double> result = new ConcurrentHashMap<>();
        meterRegistry.find("commands.executed").counters().forEach(counter -> {
            String author = counter.getId().getTag("author");
            result.put(author, counter.count());
        });
        return result;
    }

    /**
     * Увеличивает счетчик команд для указанного автора
     * (Вызывается из CommandQueueService при обработке команды)
     */
    public void incrementAuthorCommandCounter(String author) {
        authorCommandCounters.computeIfAbsent(author,
                a -> Counter.builder("commands.executed")
                        .tag("author", a)
                        .register(meterRegistry)
        ).increment();
    }
}