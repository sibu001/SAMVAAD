package com.divergent.meet.divergentmeet.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.divergent.meet.divergentmeet.DivergentmeetApplication;


@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private final String message;

	public ValidationException(String message, Throwable t) {
		super(message, t);
		this.message = message;
	}

	public ValidationException(String message) {
		super(message);
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ValidationException [logger=" + DivergentmeetApplication.LOGGER + ", message=" + message + "]";
	}
}
