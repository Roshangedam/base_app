package com.base.app.Exception;

import org.springframework.http.HttpStatus;

public class UserAuthenticationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final HttpStatus status;

	public UserAuthenticationException(HttpStatus status, String messge) {
		super(messge);
		this.status = status;
	}

	public UserAuthenticationException(String messge) {
		super(messge);
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
}