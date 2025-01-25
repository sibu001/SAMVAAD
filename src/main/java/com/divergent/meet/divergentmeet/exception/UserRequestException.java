package com.divergent.meet.divergentmeet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Error for User Request Error
 * 
 * @author Aakash
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserRequestException(String exception) {
		super(exception);
	}

}
