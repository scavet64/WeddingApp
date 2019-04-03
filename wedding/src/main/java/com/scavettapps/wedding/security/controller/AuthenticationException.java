package com.scavettapps.wedding.security.controller;

public class AuthenticationException extends RuntimeException {
    private static final long serialVersionUID = 8912411797506440492L;

    public AuthenticationException(String message, Throwable cause) {
	super(message, cause);
    }

    public AuthenticationException(String message) {
	super(message);
    }
}
