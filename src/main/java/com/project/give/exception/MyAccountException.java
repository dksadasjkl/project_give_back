package com.project.give.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MyAccountException extends RuntimeException {
    private ErrorMessage errorMessage;

    public MyAccountException(String message) {
        super(message);
        this.errorMessage = new ErrorMessage(message);
    }

    @Data
    @AllArgsConstructor
    public static class ErrorMessage { // ← static으로 변경
        private String message;
    }
}