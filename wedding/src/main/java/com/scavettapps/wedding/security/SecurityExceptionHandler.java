package com.scavettapps.wedding.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.scavettapps.wedding.core.response.ErrorResponse;
import com.scavettapps.wedding.core.response.Response;
import com.scavettapps.wedding.domain.exception.DomainObjectNotFound;


@RestControllerAdvice
public class SecurityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = { UsernameNotFoundException.class })
    public ResponseEntity<Response> usernameNotFoundException(DomainObjectNotFound ex, WebRequest request) {
        logger.debug("handling UsernameNotFoundException: " + ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(ex.getLocalizedMessage()));
    }
}