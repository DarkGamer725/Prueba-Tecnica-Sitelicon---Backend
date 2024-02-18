package com.sitelicon.api;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sitelicon.dto.UserDTO;

/**
 * This class provides methods for interacting with the API to perform CRUD
 * operations on user data. It extends the {@link ApiService} class to inherit
 * basic API connection functionality.
 *
 * <p>
 * The base URL for user-related API endpoints is defined in the {@link #URL}
 * constant. The class uses Gson for JSON serialization and deserialization.
 * 
 * @see ApiService
 */
public class ApiUserService extends ApiService {

	/**
	 * The base URL for user-related API endpoints.
	 */
	private final String URL = super.URL + "/users";

	/**
	 * Retrieves user information by ID from the API.
	 *
	 * @param id The numeric ID of the user to retrieve.
	 * @return The {@link UserDTO} object representing the user.
	 * @throws Exception If an error occurs during the API request.
	 */
	public UserDTO findUserById(long id) throws Exception {
		String body = connection.doGet(URL + "/" + id);
		Gson gson = new Gson();

		UserDTO userDTO = gson.fromJson(body, UserDTO.class);
		return userDTO;
	}

	/**
	 * Retrieves information about all users from the API.
	 *
	 * @return A list of {@link UserDTO} objects representing all users.
	 * @throws Exception If an error occurs during the API request.
	 */
	public List<UserDTO> obtainAllUsers() throws Exception {
		String body = connection.doGet(URL);
		Gson gson = new Gson();

		Type listType = new TypeToken<List<UserDTO>>() {
		}.getType();

		List<UserDTO> users = gson.fromJson(body, listType);
		return users;
	}

	/**
	 * Creates a new user by sending a {@link UserDTO} object to the API.
	 *
	 * @param userDTO The {@link UserDTO} object representing the user to be
	 *                created.
	 * @throws Exception If an error occurs during the API request.
	 */
	public void createUser(UserDTO userDTO) throws Exception {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create();
		String body = gson.toJson(userDTO);
		connection.doPost(body, URL);
	}

	/**
	 * Updates an existing user by sending a modified {@link UserDTO} object to the
	 * API.
	 *
	 * @param userDTO The {@link UserDTO} object representing the user with updated
	 *                information.
	 * @throws Exception If an error occurs during the API request.
	 */
	public void updateUser(UserDTO userDTO) throws Exception {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create();
		String body = gson.toJson(userDTO);
		connection.doUpdate(body, URL + "/" + userDTO.getId());

	}

	/**
	 * Deletes a user by ID from the API.
	 *
	 * @param id The numeric ID of the user to be deleted.
	 * @throws Exception If an error occurs during the API request.
	 */
	public void deleteUser(long id) throws Exception {
		connection.doDelete(URL + "/" + id);
	}
}
