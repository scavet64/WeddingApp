package com.scavettapps.wedding.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scavettapps.wedding.security.jwt.JwtAuthenticationRequest;
import com.scavettapps.wedding.security.services.AuthenticationService;


@RestController
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
	    throws AuthenticationException {

	// Return the token
	return ResponseEntity.ok(authenticationService.authenticateUser(authenticationRequest.getUsername(),
		authenticationRequest.getPassword()));
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
	return ResponseEntity.ok(authenticationService.refreshToken(request.getHeader(tokenHeader)));
    }

    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
