package com.sitelicon.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

/**
 * Entity class representing a user. Annotated with {@link Entity} to indicate
 * that it is a JPA entity. The ID of the user is automatically asigned by JPA.
 */
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String password;
	private Timestamp timestamp;

	/**
	 * Default constructor, mandatory for JPA to work.
	 */
	public User() {
	}

	/**
	 * Constructor to initialize a user with basic information.
	 *
	 * @param name        The user's name.
	 * @param lastName    The user's last name.
	 * @param phoneNumber The user's phone number.
	 * @param email       The user's email.
	 * @param password    The user's password.
	 */
	public User(String name, String lastName, String phoneNumber, String email, String password) {
		this.name = name;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	 * Generates a string representation of the user.
	 *
	 * @return String representation of the user.
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", password=" + password + ", timestamp=" + timestamp + "]";
	}
}
