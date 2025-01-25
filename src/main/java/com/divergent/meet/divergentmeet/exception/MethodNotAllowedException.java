package com.divergent.meet.divergentmeet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.divergent.meet.divergentmeet.DivergentmeetApplication;

/**
 * Exception for Method Not Allowed
 * 
 * @author Aakash
 *
 */
@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String message;

	public MethodNotAllowedException(String message) {
		super(message);
		this.message = message;
	}

	public MethodNotAllowedException(String message, Throwable t) {
		super(message, t);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "MethodNotAllowedException [logger=" + DivergentmeetApplication.LOGGER + ", message=" + message + "]";
	}

}
