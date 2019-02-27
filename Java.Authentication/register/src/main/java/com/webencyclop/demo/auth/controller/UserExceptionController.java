package com.webencyclop.demo.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.webencyclop.demo.auth.exception.InvalidRequestException;
import com.webencyclop.demo.auth.exception.JwtException;
import com.webencyclop.demo.auth.exception.UnauthorizeException;

@ControllerAdvice
public class UserExceptionController {
	@ExceptionHandler(value = UnauthorizeException.class)
	public ResponseEntity<Object> UnauthorizeExceptionHanlder(UnauthorizeException exception) {
	      return new ResponseEntity<>("UNAUTHORIZED REQUEST", HttpStatus.UNAUTHORIZED);
	   }
	
	@ExceptionHandler(value = InvalidRequestException.class)
	public ResponseEntity<Object> InvalidRequsetExceptionHandler(InvalidRequestException exception) {
	      return new ResponseEntity<>("INVALID REQUEST", HttpStatus.BAD_REQUEST);
	   }
	
	@ExceptionHandler(value = JwtException.class)
	public ResponseEntity<Object> JwtExceptionHandler(JwtException exception) {
	      return new ResponseEntity<>("INVALID JWT", HttpStatus.BAD_REQUEST);
	   }
	
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exception(Exception exception) {
	      return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	   }
}
