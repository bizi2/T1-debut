package com.weyland.controller;

import com.weyland.dto.Command;
import com.weyland.service.CommandQueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commands")
public class CommandController {
    private final CommandQueueService commandQueue;

    public CommandController(CommandQueueService commandQueue) {
        this.commandQueue = commandQueue;
    }

    @PostMapping
    public ResponseEntity<String> addCommand(@RequestBody Command command) {
        commandQueue.addCommand(command);
        return ResponseEntity.ok("Command added to queue");
    }
}