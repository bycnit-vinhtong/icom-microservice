package com.icommerce.payment.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handleGenericNotFoundException(NotFoundException e) {

		CustomErrorResponse error = new CustomErrorResponse("NOT_FOUND_ERROR", e.getMessage());

		error.setTimestamp(LocalDateTime.now());

		error.setStatus((HttpStatus.NOT_FOUND.value()));

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(value = InvalidInputException.class)
	public ResponseEntity<CustomErrorResponse> handleGenericInvalidInputException(InvalidInputException e) {

		CustomErrorResponse error = new CustomErrorResponse("INVALID_INPUT", e.getMessage());

		error.setTimestamp(LocalDateTime.now());

		error.setStatus((HttpStatus.BAD_REQUEST.value()));

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

}
