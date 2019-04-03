package com.scavettapps.wedding.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.scavettapps.wedding.core.response.ErrorResponse;
import com.scavettapps.wedding.core.response.Response;
import com.scavettapps.wedding.domain.exception.DomainObjectNotFound;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = { DomainObjectNotFound.class })
    public ResponseEntity<Response> domainObjectNotFound(DomainObjectNotFound ex, WebRequest request) {
	log.debug("handling DomainObjectNotFound: " + ex);
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getLocalizedMessage()));
    }
}