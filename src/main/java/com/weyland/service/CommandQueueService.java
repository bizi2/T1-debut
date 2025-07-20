package com.weyland.service;

import com.weyland.dto.Command;
import io.micrometer.core.instrument.*;
import org.springframework.stereotype.Service;
import com.weyland.dto.Priority;
import com.weyland.dto.Command;
import com.weyland.exception.CommandQueueFullException; // Добавьте этот импорт
import java.util.concurrent.*;

@Service
public class CommandQueueService {
    private final MetricsService metricsService;
    private final ExecutorService criticalExecutor = Executors.newSingleThreadExecutor();
    private final ThreadPoolExecutor commonExecutor = new ThreadPoolExecutor(
            1, 1, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(100)
    );

    public CommandQueueService(MetricsService metricsService) {
        this.metricsService = metricsService;
        // Регистрация метрики размера очереди
        Gauge.builder("queue.size", commonExecutor.getQueue()::size)
                .register(metricsService.getMeterRegistry());
    }

    public void addCommand(Command command) {
        if (command.priority() == Priority.CRITICAL) {
            criticalExecutor.execute(() -> processCommand(command));
        } else {
            if (commonExecutor.getQueue().remainingCapacity() == 0) {
                throw new CommandQueueFullException("Queue is full");
            }
            commonExecutor.submit(() -> processCommand(command));
        }
    }

    private void processCommand(Command command) {
        metricsService.recordCommandExecution(command.author());
        System.out.printf("[EXECUTED] %s (Priority: %s)%n",
                command.description(), command.priority());
    }
}