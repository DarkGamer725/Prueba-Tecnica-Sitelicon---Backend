package com.sitelicon.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitelicon.exceptions.ContactNotFound;
import com.sitelicon.model.Contact;
import com.sitelicon.service.ContactService;

/**
 * Controller class for handling HTTP requests related to contact operations.
 * 
 * @see {@link Contact}
 * @see {@link UserContact}
 */
@RestController
@RequestMapping("/api/contacts")
public class ContactController {

	@Autowired
	private ContactService contactService;

	/**
	 * Retrieves a list of all contacts.
	 *
	 * @return ResponseEntity with a list of contacts if successful, or an empty
	 *         list if no contacts are found.
	 */
	@GetMapping
	public ResponseEntity<List<Contact>> getAllContacts() {
		return ResponseEntity.ok(contactService.getAllContacts());
	}

	/**
	 * Retrieves a contact by its unique identifier.
	 *
	 * @param id The unique identifier of the contact.
	 * @return ResponseEntity with the contact if found, or a 404 Not Found response
	 *         if the contact does not exist.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
		Contact contact = null;
		try {
			contact = contactService.getContactById(id);
			return ResponseEntity.ok(contact);
		} catch (ContactNotFound e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Creates a new contact.
	 *
	 * @param contact The contact object to be created.
	 * @return ResponseEntity with a 201 Created status if successful.
	 */
	@PostMapping
	public ResponseEntity<Void> createContact(@RequestBody Contact contact) {
		contactService.createContact(contact);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * Updates an existing contact.
	 *
	 * @param id             The unique identifier of the contact to be updated.
	 * @param updatedContact The updated contact object.
	 * @return ResponseEntity with a 200 OK status if successful, or a 404 Not Found
	 *         response if the contact does not exist.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
		try {
			contactService.updateContact(id, updatedContact);
			return ResponseEntity.ok().build();
		} catch (ContactNotFound e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Deletes a contact by its unique identifier.
	 *
	 * @param id The unique identifier of the contact to be deleted.
	 * @return ResponseEntity with a 200 OK status if successful, or a 404 Not Found
	 *         response if the contact does not exist.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
		try {
			contactService.deleteContact(id);
			return ResponseEntity.ok().build();
		} catch (ContactNotFound e) {
			return ResponseEntity.notFound().build();
		}
	}
}
