package com.example.demo.configAuthen;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthExceptionController {
	@ExceptionHandler(value = UnauthorizeException.class)
	public ResponseEntity<Object> UnauthorizeExceptionHanlder(UnauthorizeException exception) {
	      return new ResponseEntity<>("UNAUTHORIZED REQUEST", HttpStatus.UNAUTHORIZED);
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
