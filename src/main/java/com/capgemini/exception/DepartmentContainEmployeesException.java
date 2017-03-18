package com.capgemini.exception;

public class DepartmentContainEmployeesException extends IllegalStateException {
	
	private static final long serialVersionUID = 1L;
	
	public DepartmentContainEmployeesException(String msg) {
		super(msg);
	}

}
