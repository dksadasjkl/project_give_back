package com.project.give.exception;

import lombok.Getter;

import java.util.Map;

public class ValidException extends RuntimeException {

    @Getter
    Map<String, String> errorMap;

    public ValidException(Map<String, String> errorMap) {
        super("유효성 검사 오류");
        this.errorMap = errorMap;
    }

}