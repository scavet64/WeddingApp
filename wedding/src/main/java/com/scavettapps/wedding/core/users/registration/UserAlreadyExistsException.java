package com.scavettapps.wedding.core.users.registration;

public class UserAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -4231863225904067759L;

    public UserAlreadyExistsException() {
	// TODO Auto-generated constructor stub
    }

    public UserAlreadyExistsException(String message) {
	super(message);
	// TODO Auto-generated constructor stub
    }

    public UserAlreadyExistsException(Throwable cause) {
	super(cause);
	// TODO Auto-generated constructor stub
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
	super(message, cause);
	// TODO Auto-generated constructor stub
    }

    public UserAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
	// TODO Auto-generated constructor stub
    }

}
