package com.sitelicon.api;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sitelicon.dto.ContactDTO;

/**
 * This class provides methods for interacting with the API to perform CRUD
 * operations on contact data. It extends the {@link ApiService} class to
 * inherit basic API connection functionality.
 *
 * <p>
 * The base URL for contact-related API endpoints is defined in the {@link #URL}
 * constant. The class uses Gson for JSON serialization and deserialization.
 * 
 * @see ApiService
 */
public class ApiContactService extends ApiService {
	/**
	 * The base URL for contact-related API endpoints.
	 */
	private final String URL = super.URL + "/contacts";

	/**
	 * Retrieves contact information by ID from the API.
	 *
	 * @param id The numeric ID of the contact to retrieve.
	 * @return The {@link ContactDTO} object representing the contact.
	 * @throws Exception If an error occurs during the API request.
	 */
	public ContactDTO findContactById(long id) throws Exception {
		String body = connection.doGet(URL + "/" + id);
		Gson gson = new Gson();

		ContactDTO contactDTO = gson.fromJson(body, ContactDTO.class);
		return contactDTO;
	}

	/**
	 * Retrieves information about all contacts from the API.
	 *
	 * @return A list of {@link ContactDTO} objects representing all contacts.
	 * @throws Exception If an error occurs during the API request.
	 */
	public List<ContactDTO> obtainAllContacts() throws Exception {
		String body = connection.doGet(URL);
		Gson gson = new Gson();

		Type listType = new TypeToken<List<ContactDTO>>() {
		}.getType();

		List<ContactDTO> contacts = gson.fromJson(body, listType);
		return contacts;
	}

	/**
	 * Creates a new contact by sending a {@link ContactDTO} object to the API.
	 *
	 * @param contactDTO The {@link ContactDTO} object representing the contact to
	 *                   be created.
	 * @throws Exception If an error occurs during the API request.
	 */
	public void createContact(ContactDTO contactDTO) throws Exception {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create();
		String body = gson.toJson(contactDTO);
		connection.doPost(body, URL);
	}

	/**
	 * Updates an existing contact by sending a modified {@link ContactDTO} object
	 * to the API.
	 *
	 * @param contactDTO The {@link ContactDTO} object representing the contact with
	 *                   updated information.
	 * @throws Exception If an error occurs during the API request.
	 */
	public void updateContact(ContactDTO contactDTO) throws Exception {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create();
		String body = gson.toJson(contactDTO);
		connection.doUpdate(body, URL + "/" + contactDTO.getId());

	}

	/**
	 * Deletes a contact by ID from the API.
	 *
	 * @param id The numeric ID of the contact to be deleted.
	 * @throws Exception If an error occurs during the API request.
	 */
	public void deleteContact(String id) throws Exception {
		connection.doDelete(URL + "/" + id);
	}
}
