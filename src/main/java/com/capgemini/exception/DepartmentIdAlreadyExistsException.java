package com.capgemini.exception;

public class DepartmentIdAlreadyExistsException extends IllegalStateException {
	
	private static final long serialVersionUID = 1L;
	
	public DepartmentIdAlreadyExistsException(String msg) {
		super(msg);
	}

}
