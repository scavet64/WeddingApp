package com.scavettapps.wedding.core.users.registration;

import com.scavettapps.wedding.domain.WeddingUser;
import com.scavettapps.wedding.security.jwt.JsonWebToken;

public class RegistrationResponse {

    private WeddingUser newUser;
    private JsonWebToken token;

    /**
     * @param newUser
     * @param token
     */
    public RegistrationResponse(WeddingUser newUser, JsonWebToken token) {
	super();
	this.newUser = newUser;
	this.token = token;
    }

    /**
     * @return the newUser
     */
    public WeddingUser getNewUser() {
	return newUser;
    }

    /**
     * @param newUser the newUser to set
     */
    public void setNewUser(WeddingUser newUser) {
	this.newUser = newUser;
    }

    /**
     * @return the token
     */
    public JsonWebToken getToken() {
	return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(JsonWebToken token) {
	this.token = token;
    }

}
