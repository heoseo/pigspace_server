package com.pigplace.common.support;


import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionController {
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(ExceptionController.class);
//	@Autowired
//	private ExceptionService exceptionService;

//	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//	protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
//			HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
////		exceptionService.errorLog(e, request);
//		return new ResponseEntity<>(
//				new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), "MethodNotSupported", e.getMessage()),
//				HttpStatus.METHOD_NOT_ALLOWED);
//	}

	@ExceptionHandler(NoHandlerFoundException.class)
	protected ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException e,
			HttpServletRequest request) {
//		exceptionService.errorLog(e, request);
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Not Found", e.getMessage()),
				HttpStatus.NOT_FOUND);
	}

//	@ExceptionHandler(Exception.class)
//	protected ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
////		exceptionService.errorLog(e, request);
//		return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), request.getSession().getId()),
//				HttpStatus.BAD_REQUEST);
//	}

//	@ExceptionHandler(JwtException.class)
//	protected ResponseEntity<ErrorResponse> handleJwtException(JwtException e, HttpServletRequest request) {
////		exceptionService.errorLog(e, request);
//		return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "JwtException", e.getMessage()),
//				HttpStatus.UNAUTHORIZED);
//	}

//	@ExceptionHandler(AuthenticationException.class)
//	protected ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e,
//			HttpServletRequest request) {
////		exceptionService.errorLog(e, request);
//		return new ResponseEntity<>(
//				new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "AuthenticationException", e.getMessage()),
//				HttpStatus.UNAUTHORIZED);
//	}
}
