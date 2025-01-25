package com.divergent.meet.divergentmeet.exception;

import com.divergent.meet.divergentmeet.DivergentmeetApplication;

/**
 * Generic Exception <br>
 * 
 * @author Aakash
 *
 */
public class GenricException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public final String message;

	public GenricException(String message) {
		super(message);
		this.message = message;
	}

	public GenricException(String message, Throwable t) {
		super(message, t);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "GenricException [logger=" + DivergentmeetApplication.LOGGER + ", message=" + message + "]";
	}

}
