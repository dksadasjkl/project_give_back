package com.project.give.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/*
 * 모든 컨트롤러에서 발생하는 예외를 한 곳에서 처리하기 위한 클래스
 * @RestControllerAdvice : 전역 예외 처리 + @ResponseBody 포함
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * BadCredentialsException 발생 시 처리
     * - 주로 로그인 실패, 비밀번호 불일치 시 발생
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MyAccountException.ErrorMessage> handleBadCredentials(BadCredentialsException ex) {
        // MyAccountException.ErrorMessage : 메시지를 담는 DTO
        MyAccountException.ErrorMessage errorMessage = new MyAccountException.ErrorMessage(ex.getMessage());

        // HTTP 상태 코드 400(BAD_REQUEST)와 함께 메시지 반환
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    /*
     * 모든 예외(Exception) 발생 시 처리
     * 예상치 못한 예외를 잡아 서버 에러로 응답
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyAccountException.ErrorMessage> handleException(Exception ex) {
        // MyAccountException.ErrorMessage : 메시지를 담는 DTO
        MyAccountException.ErrorMessage errorMessage = new MyAccountException.ErrorMessage(ex.getMessage());

        // HTTP 상태 코드 500(INTERNAL_SERVER_ERROR)과 함께 메시지 반환
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(ValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidException(ValidException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "유효성 검사 오류");
        result.put("errors", e.getErrorMap()); // 실제 필드 에러 전달
        return ResponseEntity.badRequest().body(result);
    }
}