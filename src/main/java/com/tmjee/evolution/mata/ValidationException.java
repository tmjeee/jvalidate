package com.tmjee.evolution.mata;

import java.util.List;

/**
 * @author tmjee
 */
public class ValidationException extends RuntimeException {

    private final List<String> messages;

    public ValidationException(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
