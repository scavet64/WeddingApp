package com.scavettapps.wedding.domain.exception;

public class DomainObjectNotFound extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DomainObjectNotFound() {
        super("Domain Object was not found");
    }

    public DomainObjectNotFound(String message) {
        super(message);
    }

    public DomainObjectNotFound(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public DomainObjectNotFound(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public DomainObjectNotFound(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
