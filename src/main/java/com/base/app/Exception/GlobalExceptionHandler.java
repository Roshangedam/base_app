package com.base.app.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(UserAuthenticationException.class)
    public ResponseEntity<Object> incorrectPasswordExceptions(UserAuthenticationException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
	
	// Default exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
