package com.scavettapps.wedding.core.weddingfile;

public class WeddingFileUploadRequest {

	private String name;
	private String comment;
	private String eventId;

	/**
	 * 
	 */
	public WeddingFileUploadRequest() {
		super();
	}

	/**
	 * @param name
	 * @param comment
	 * @param eventId
	 */
	public WeddingFileUploadRequest(String name, String comment, String eventId) {
		super();
		this.name = name;
		this.comment = comment;
		this.eventId = eventId;
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
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

}
