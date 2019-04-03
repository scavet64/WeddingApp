package com.scavettapps.wedding.core.users.registration;

public class RegistrationRequest {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;

    /**
     * @return the username
     */
    public String getUsername() {
	return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
	this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
	this.password = password;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
	return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
	return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
	return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
	this.email = email;
    }

}
