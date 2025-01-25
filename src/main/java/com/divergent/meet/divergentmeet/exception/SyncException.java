package com.divergent.meet.divergentmeet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for Synchronization failure
 * 
 * @author Aakash
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SyncException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4461389906311239435L;

	public SyncException(String message) {
		super(message);
	}

	public SyncException(String message, Throwable cause) {
		super(message, cause);
	}
}