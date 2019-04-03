package com.scavettapps.wedding.security.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.scavettapps.wedding.security.controller.AuthenticationException;
import com.scavettapps.wedding.security.jwt.JsonWebToken;
import com.scavettapps.wedding.security.jwt.JwtTokenUtil;
import com.scavettapps.wedding.security.jwt.JwtUser;


@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * Authenticates a user using the provided username and password
     * 
     * @param authenticationRequest request containing the username and password
     * @return a Json Web Token if the request is valid
     */
    public JsonWebToken authenticateUser(String username, String password) {
	authenticate(username, password);
	return getTokenForUser(username, password);
    }

    public JsonWebToken getTokenForUser(String username, String password) {
	// Reload password post-security so we can generate the token
	final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	final String token = jwtTokenUtil.generateToken(userDetails);

	return new JsonWebToken(token);
    }

    /**
     * Returns a newly refreshed Json Web Token. If something is wrong, an
     * {@link AuthenticationException} will be thrown
     * 
     * @param tokenHeader the token header
     * @return a newly refreshed Json Web Token
     */
    public JsonWebToken refreshToken(String tokenHeader) {
	final String token = tokenHeader.substring(7);
	final String username = jwtTokenUtil.getUsernameFromToken(token);
	final JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

	if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
	    String refreshedToken = jwtTokenUtil.refreshToken(token);
	    return new JsonWebToken(refreshedToken);
	} else {
	    throw new AuthenticationException("Could not refresh token");
	}
    }

    /**
     * Authenticates the user. If something is wrong, an
     * {@link AuthenticationException} will be thrown
     */
    private void authenticate(String username, String password) {
	Objects.requireNonNull(username);
	Objects.requireNonNull(password);

	try {
	    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	} catch (DisabledException e) {
	    throw new AuthenticationException("User is disabled!", e);
	} catch (BadCredentialsException e) {
	    throw new AuthenticationException("Bad credentials!", e);
	} catch (Exception ex) {
	    throw new AuthenticationException("Dont know!", ex);
	}
    }
}
