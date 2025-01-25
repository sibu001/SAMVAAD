package com.divergent.meet.divergentmeet.exception.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.divergent.meet.divergentmeet.DivergentmeetApplication;
import com.divergent.meet.divergentmeet.exception.GenricException;
import com.divergent.meet.divergentmeet.exception.SyncException;
import com.divergent.meet.divergentmeet.exception.UserRequestException;
import com.divergent.meet.divergentmeet.exception.ValidationException;


/**
 * Global Exception Handler <br>
 * response formatting for Exceptions
 * 
 * @author Ravi
 *
 */
@SuppressWarnings("rawtypes")
@RestControllerAdvice
@Order(1)
public class GlobalExceptionHandler {
	private static final String EXCEPTION_OCCURED = "Exception Occured:: {}";

	public static final String MESSAGE = "message";
	public static final String STATUS = "status";

	@ExceptionHandler(UserRequestException.class)
	public ResponseEntity<Map> handleUserNotFoundException(HttpServletRequest request, UserRequestException ex) {
		DivergentmeetApplication.LOGGER.error(EXCEPTION_OCCURED, ex.getMessage());
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put(STATUS, HttpStatus.BAD_REQUEST.value());
		returnMap.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(returnMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map> handleValidationException(HttpServletRequest request, ValidationException ex) {
		DivergentmeetApplication.LOGGER.error(EXCEPTION_OCCURED, ex.getMessage());
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put(STATUS, HttpStatus.BAD_REQUEST.value());
		returnMap.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(returnMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map> handleMethodNotAllowedException(HttpServletRequest request,
			HttpRequestMethodNotSupportedException ex) {
		DivergentmeetApplication.LOGGER.error(EXCEPTION_OCCURED, ex.getMessage());
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put(STATUS, HttpStatus.METHOD_NOT_ALLOWED.value());
		returnMap.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(returnMap, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(GenricException.class)
	public ResponseEntity<Map> handleGenricException(HttpServletRequest request, GenricException ex) {
		DivergentmeetApplication.LOGGER.error(EXCEPTION_OCCURED, ex.getMessage());
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put(STATUS, HttpStatus.EXPECTATION_FAILED.value());
		returnMap.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(returnMap, HttpStatus.EXPECTATION_FAILED);

	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Map> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
		DivergentmeetApplication.LOGGER.error(EXCEPTION_OCCURED, ex.getMessage());
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put(STATUS, HttpStatus.FORBIDDEN.value());
		returnMap.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(returnMap, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(SyncException.class)
	public ResponseEntity<Map> handleSyncException(SyncException ex, HttpServletRequest request) {
		DivergentmeetApplication.LOGGER.error(EXCEPTION_OCCURED, ex.getMessage());
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put(STATUS, HttpStatus.FORBIDDEN.value());
		returnMap.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(returnMap, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map> handleMethodArgumentNotValidException(ConstraintViolationException ex,
			HttpServletRequest request) {
		DivergentmeetApplication.LOGGER.error(EXCEPTION_OCCURED, ex.getMessage());
		List<String> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put(STATUS, HttpStatus.FORBIDDEN.value());
		returnMap.put(MESSAGE, errors.toString());
		return new ResponseEntity<>(returnMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map> exception(HttpServletRequest request, GenricException ex) {
		DivergentmeetApplication.LOGGER.error(EXCEPTION_OCCURED, ex.getMessage());
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
		returnMap.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(returnMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
