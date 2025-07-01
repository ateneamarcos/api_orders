package com.example.demo.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> notFound(ResourceNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ExceptionHandler(InvalidOrderStateException.class)
	public ResponseEntity<String> invalidState(InvalidOrderStateException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<String> business(BusinessException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}