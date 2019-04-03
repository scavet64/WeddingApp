package com.scavettapps.wedding.domain;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class WeddingUser implements Comparable<WeddingUser> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	Long id;

	@NotEmpty
	@Column(name = "username")
	private String username;

	@JsonIgnore
	@NotEmpty
	@Column(name = "password")
	private String password;

	@Column(name = "firstname", length = 50)
	@NotNull
	@Size(min = 4, max = 50)
	private String firstname;

	@Column(name = "lastname", length = 50)
	@NotNull
	@Size(min = 4, max = 50)
	private String lastname;

	@Column(name = "email", length = 50)
	@NotNull
	@Size(min = 4, max = 50)
	@Email
	private String email;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "token_expired")
	private boolean tokenExpired;

	@Column(name = "last_password_reset")
	@NotNull
	private Timestamp lastPasswordResetDate;

	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonIgnore
	private List<Role> roles = new ArrayList<>();

	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "users_events", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
	@JsonIgnore
	private List<Event> groups = new ArrayList<>();

	/**
	 * Full Args Constructor
	 * 
	 * @param id
	 * @param username
	 * @param password
	 * @param roles
	 */
	public WeddingUser(Long id, @NotEmpty String username, @NotEmpty String password, List<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	/**
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param email
	 */
	public WeddingUser(@NotEmpty String username, @NotEmpty String password,
			@NotNull @Size(min = 4, max = 50) String firstname, @NotNull @Size(min = 4, max = 50) String lastname,
			@NotNull @Size(min = 4, max = 50) @Email String email, List<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.roles.addAll(roles);
		enabled = true;
		tokenExpired = true;
		lastPasswordResetDate = Timestamp.from(Instant.now());
	}

	/**
	 * No Args Constructor
	 */
	public WeddingUser() {
		super();
		lastPasswordResetDate = Timestamp.from(Instant.now());
	}

	public WeddingUser(@NotEmpty String username) {
		super();
		this.username = username;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param list the roles to set
	 */
	public void setRoles(List<Role> list) {
		roles = list;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the tokenExpired
	 */
	public boolean isTokenExpired() {
		return tokenExpired;
	}

	/**
	 * @param tokenExpired the tokenExpired to set
	 */
	public void setTokenExpired(boolean tokenExpired) {
		this.tokenExpired = tokenExpired;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
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

	/**
	 * @return the lastPasswordResetDate
	 */
	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	/**
	 * @param lastPasswordResetDate the lastPasswordResetDate to set
	 */
	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	/**
	 * @return the groups
	 */
	public List<Event> getGroups() {
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(List<Event> groups) {
		this.groups = groups;
	}

	@Override
	public int compareTo(WeddingUser o) {
		// TODO Auto-generated method stub
		return 0;
	}

}