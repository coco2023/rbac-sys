package com.microprac.productservice.exception;

import com.microprac.productservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<ErrorResponse> handelProductServiceException(ProductServiceException exception){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getStatusCode())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }
}
