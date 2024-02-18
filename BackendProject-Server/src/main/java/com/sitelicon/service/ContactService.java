package com.sitelicon.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitelicon.exceptions.ContactNotFound;
import com.sitelicon.exceptions.UserNotFound;
import com.sitelicon.model.Contact;
import com.sitelicon.repository.ContactRepository;

/**
 * Service class that handles business logic related to contact operations.
 * 
 * @see {@link Contact}
 * @see {@link ContactRepository}
 */
@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	/**
	 * Retrieves a list of all contacts.
	 *
	 * @return List of contacts.
	 */
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	/**
	 * Retrieves a contact by its unique identifier.
	 *
	 * @param id The unique identifier of the contact.
	 * @return Contact object if found.
	 * @throws ContactNotFound If the contact with the specified ID is not found.
	 */
	public Contact getContactById(Long id) throws ContactNotFound {
		return contactRepository.findById(id).orElseThrow(() -> new ContactNotFound());
	}

	/**
	 * Creates a new contact.
	 *
	 * @param user The contact object to be created.
	 */
	public void createContact(Contact contact) {
		contactRepository.save(contact);
	}

	/**
	 * Updates an existing contact. Updates all the fields except ID and Timestamp
	 *
	 * @param id          The unique identifier of the contact to be updated.
	 * @param updatedUser The updated contact object.
	 * @throws UserNotFound If the contact with the specified ID is not found.
	 */
	public void updateContact(Long id, Contact updatedContact) throws ContactNotFound {
		Contact contactToUpdate = contactRepository.findById(id).orElseThrow(() -> new ContactNotFound());

		BeanUtils.copyProperties(updatedContact, contactToUpdate, "id", "timestamps");

		contactRepository.save(contactToUpdate);
	}

	/**
	 * Deletes a contact by its unique identifier.
	 *
	 * @param id The unique identifier of the contact to be deleted.
	 * @throws UserNotFound If the contact with the specified ID is not found.
	 */
	public void deleteContact(Long id) throws ContactNotFound {
		contactRepository.findById(id).orElseThrow(() -> new ContactNotFound());
		contactRepository.deleteById(id);
	}
}
