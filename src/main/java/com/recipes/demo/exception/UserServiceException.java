package com.recipes.demo.exception;

public class UserServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3999306802613997268L;

	public UserServiceException(String message) {
		super(message);
	}
}
