package com.capgemini.exception;

public class ManagerDeletionException extends IllegalStateException{ //wykorzystać

	private static final long serialVersionUID = 3246041160832027158L;
	
	public ManagerDeletionException(String msg) {
		super(msg);
	}

}
