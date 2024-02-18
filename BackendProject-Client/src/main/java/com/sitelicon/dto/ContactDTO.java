package com.sitelicon.dto;

import java.sql.Timestamp;

/**
 * Data Transfer Object (DTO) representing a contact.
 * <p>
 * This class encapsulates information about a contact, including attributes
 * such as name, email, reason, message, and a timestamp indicating the time of
 * creation. The class provides getters and setters for each attribute to access
 * and modify the contact information.
 * 
 * @see {@link Reason}
 */
public class ContactDTO {
	private Long id;
	private String name;
	private String email;
	private Reason reason;
	private String message;
	private Timestamp timestamp;

	/**
	 * Constructs a UserDTO object with the specified information.
	 * 
	 * @param name    The user's name
	 * @param email   The user's email
	 * @param reason  The {@link Reason} of the contact
	 * @param message The message the user wants to send
	 * 
	 * @see {@link Reason}
	 */
	public ContactDTO(String name, String email, Reason reason, String message) {
		super();
		this.name = name;
		this.email = email;
		this.reason = reason;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Returns a string representation of the ContactDTO object.
	 *
	 * @return A string containing the contact information, including ID, name,
	 *         email, reason, message, and creation timestamp.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Contact id: ").append(id)
		.append("\nName: ").append(name)
		.append("\nEmail: ").append(email)
		.append("\nReason: ").append(reason)
		.append("\nMessage: ").append(message)
		.append("\nTime of creation: ").append(timestamp);
		return sb.toString();
	}

}
