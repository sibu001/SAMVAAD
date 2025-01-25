package com.divergent.meet.divergentmeet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for internal Application error
 * <br>
 * <b>Error Code</b>:- {@code 500}
 * 
 * @author Aakash
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AppException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4461389906311239435L;

	public AppException(String message) {
		super(message);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}
}