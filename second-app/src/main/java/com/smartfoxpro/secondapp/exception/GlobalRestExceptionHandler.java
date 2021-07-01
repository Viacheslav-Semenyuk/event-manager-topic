package com.smartfoxpro.secondapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<CustomExceptionResponse> handleException(RuntimeException e) {
        HttpStatusCodeException statusCodeException = (HttpStatusCodeException) e;
        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse();
        exceptionResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(exceptionResponse, statusCodeException.getStatusCode());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class CustomExceptionResponse {
        private String message;
    }

}
