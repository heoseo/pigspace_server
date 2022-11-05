package com.pigspace.common.support;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionController extends LogObject{

	private static final long serialVersionUID = 9128046630036041496L;

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

	/**
	 * 404
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	protected ErrorResponse handleNoHandlerFoundException(NoHandlerFoundException e,
			HttpServletRequest request) {
		return new ErrorResponse(ErrorCode.NOT_FOUND);
	}

	/**
	 * PigException
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	protected ErrorResponse handlePigException(Exception e,
			HttpServletRequest request) {
		return new ErrorResponse(ErrorCode.INTER_SERVER_ERROR, e.getMessage());
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
