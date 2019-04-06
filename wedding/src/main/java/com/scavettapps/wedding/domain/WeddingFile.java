package com.scavettapps.wedding.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "files")
public class WeddingFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "content_id")
	private Long id;

	@Column(name = "sha256", length = 64)
	@NotNull
	@Size(min = 64, max = 64)
	private String sha256;

	@Column(name = "name", length = 255)
	@NotNull
	@Size(min = 1, max = 255)
	private String name;

	@Column(name = "comment", length = 255)
	@NotNull
	@Size(min = 1, max = 255)
	private String comment;

	@Column(name = "extension", length = 255)
	@NotNull
	@Size(min = 1, max = 255)
	private String extension;

	@NotNull
	@Column(name = "created_on")
	private Date createdOn;

	@NotNull
	@ManyToOne()
	@JoinColumn(name="user_id")
	private WeddingUser user;

	@NotNull
	@ManyToOne()
	@JoinColumn(name="event_id")
	private Event event;

	/**
	 * 
	 */
	public WeddingFile() {
		super();
	}

	/**
	 * @param sha256
	 * @param name
	 * @param user
	 * @param event
	 */
	public WeddingFile(@NotNull @Size(min = 64, max = 64) String sha256, @NotNull @Size(min = 1, max = 255) String name,
			WeddingUser user, Event event, @NotNull @Size(min = 1, max = 255) String extension) {
		super();
		this.sha256 = sha256;
		this.name = name;
		this.user = user;
		this.event = event;
		this.extension = extension;
		this.createdOn = new Date();
	}

	/**
	 * @param sha256
	 * @param name
	 * @param user
	 * @param event
	 */
	public WeddingFile(@NotNull @Size(min = 64, max = 64) String sha256, @NotNull @Size(min = 1, max = 255) String name,
			WeddingUser user, Event event, @Size(min = 1, max = 255) String comment,
			@NotNull @Size(min = 1, max = 255) String extension) {
		super();
		this.sha256 = sha256;
		this.name = name;
		this.user = user;
		this.event = event;
		this.comment = comment;
		this.extension = extension;
		this.createdOn = new Date();
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
	 * @return the sha256
	 */
	public String getSha256() {
		return sha256;
	}

	/**
	 * @param sha256 the sha256 to set
	 */
	public void setSha256(String sha256) {
		this.sha256 = sha256;
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
	 * @return the user
	 */
	public WeddingUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(WeddingUser user) {
		this.user = user;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the timeRecorded
	 */
	public Date getTimeRecorded() {
		return createdOn;
	}

	/**
	 * @param timeRecorded the timeRecorded to set
	 */
	public void setTimeRecorded(Date timeRecorded) {
		this.createdOn = timeRecorded;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	

}
