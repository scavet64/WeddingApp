package com.scavettapps.wedding.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Long id;

	@Column(name = "uuid", length = 36)
	@NotNull
	@Size(min = 36, max = 36)
	private String uuid;

	@Column(name = "name", length = 255)
	@NotNull
	@Size(min = 1, max = 255)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_events", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonBackReference
	private Set<WeddingUser> users;

	public Event() {
		super();
	}

	/**
	 * @param uuid
	 * @param name
	 */
	public Event(@NotNull @Size(min = 36, max = 36) String uuid, @NotNull @Size(min = 1, max = 255) String name) {
		super();
		this.uuid = uuid;
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the users
	 */
	public Set<WeddingUser> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<WeddingUser> users) {
		this.users = users;
	}

	/**
	 * @return the guid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param guid the guid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
