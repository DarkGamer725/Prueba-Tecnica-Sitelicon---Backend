
package com.sitelicon.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

/**
 * Entity class representing a contact form submission. Annotated with
 * {@link Entity} to indicate that it is a JPA entity. The ID of the contact is
 * automatically asigned by JPA.
 * 
 * @see {@link Reason}
 */
@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private Reason reason;
	private String message;
	private Timestamp timestamp;

	/**
	 * Default constructor, mandatory for JPA to work.
	 */
	public Contact() {

	}

	/**
	 * Constructor to initialize a contact with basic information.
	 *
	 * @param name    The user's name.
	 * @param email   The user's email.
	 * @param reason  The {@link Reason} for contact.
	 * @param message The contact message.
	 * 
	 */
	public Contact(String name, String email, Reason reason, String message) {
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
	 * Gets called before the entity is persisted. Sets the creation timestamp using
	 * the current system time.
	 */
	@PrePersist
	protected void onCreate() {
		timestamp = new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Generates a string representation of the contact.
	 *
	 * @return String representation of the contact.
	 */
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", reason=" + reason + ", message="
				+ message + ", timestamp=" + timestamp + "]";
	}
}
