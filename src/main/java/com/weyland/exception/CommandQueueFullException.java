package com.weyland.exception;
import com.weyland.exception.CommandQueueFullException;
public class CommandQueueFullException extends RuntimeException {
    public CommandQueueFullException(String message) {
        super(message);
    }
}