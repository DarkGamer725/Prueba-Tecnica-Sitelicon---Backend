package com.sitelicon.dto;

import java.sql.Timestamp;

/**
 * Data Transfer Object (DTO) representing a user.
 * <p>
 * This class encapsulates information about a user, including attributes such
 * as name, last name, phone number, email, password, and a timestamp indicating
 * the time of creation. The class provides getters and setters for each
 * attribute to access and modify the user information.
 */
public class UserDTO {
	private Long id;
	private String name;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String password;
	private Timestamp timestamp;

	/**
	 * Constructs a UserDTO object with the specified information.
	 *
	 * @param name        The user's name.
	 * @param lastName    The user's last name.
	 * @param phoneNumber The user's phone number.
	 * @param email       The user's email address.
	 * @param password    The user's password.
	 */
	public UserDTO(String name, String lastName, String phoneNumber, String email, String password) {
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
	 * Returns a string representation of the UserDTO object.
	 *
	 * @return A string containing the user's information, including ID, name, last
	 *         name, phone number, email, and creation timestamp.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User id: ").append(id)
		.append("\nName: ").append(name)
		.append("\nLast name: ").append(lastName)
		.append("\nPhone number: ").append(phoneNumber)
		.append("\nEmail: ").append(email)
		.append("\nTime of creation: ").append(timestamp);
		return sb.toString();
	}

}
