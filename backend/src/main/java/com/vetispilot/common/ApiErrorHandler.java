package com.vetispilot.common;

import com.vetispilot.auth.LoginFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Turns errors into a small, always equal JSON: {"status": 401, "error": "WRONG_CREDENTIALS", "message": "..."} */
@RestControllerAdvice
public class ApiErrorHandler {

    public record ApiError(int status, String error, String message) { }

    @ExceptionHandler(LoginFailedException.class)
    ResponseEntity<ApiError> loginFailed(LoginFailedException e) {
        HttpStatus status = e.getReason() == LoginFailedException.Reason.LOCKED
                ? HttpStatus.TOO_MANY_REQUESTS : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(new ApiError(status.value(), e.getReason().name(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> invalid(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(new ApiError(400, "INVALID_INPUT", "Please fill in all required fields."));
    }
}
