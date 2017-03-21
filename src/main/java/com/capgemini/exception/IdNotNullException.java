package com.capgemini.exception;

public class IdNotNullException extends IllegalStateException {

	private static final long serialVersionUID = -614775163501284335L;

	public IdNotNullException(String msg) {
		super(msg);
	}
}
