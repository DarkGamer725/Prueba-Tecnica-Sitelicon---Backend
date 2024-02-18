package com.sitelicon.service;

import java.net.ConnectException;
import java.util.List;
import java.util.Scanner;

import com.sitelicon.api.ApiContactService;
import com.sitelicon.dto.ContactDTO;
import com.sitelicon.dto.Reason;
import com.sitelicon.exceptions.NotFoundException;
import com.sitelicon.util.Utilities;

/**
 * Service class responsible for handling contact-related operations by
 * interacting with the API through {@link ApiContactService}.
 * <p>
 * This class provides methods for finding, creating, updating, and deleting
 * contacts. It communicates with the API service and handles user input and
 * exceptions that may occur during these operations.
 *
 * @see ApiContactService
 * @see ContactDTO
 * @see NotFoundException
 * @see ConnectException
 * @see Utilities
 */
public class ContactService {

	private final ApiContactService apiContactService = new ApiContactService();

	/**
	 * Asks the user for a numeric ID, retrieves and prints information about the
	 * contact found, or displays a message if the contact wasn't found.
	 * 
	 * @param sc A Scanner object for reading user input.
	 * @return The {@link ContactDTO} found. Null if it wasn't found.
	 * @see ApiContactService#findContactById(int)
	 */
	public ContactDTO findContactById(Scanner sc) {
		System.out.println("\n-- Finding Contact --");
		long id = Utilities.promptLong(sc, "Enter the contact Id: ");
		ContactDTO contactDTO = null;
		try {
			contactDTO = apiContactService.findContactById(id);
			System.out.println("- Contact found -\n" + contactDTO);
		} catch (NotFoundException e) {
			System.out.println("Couldn't find the contact by id: " + id);
		} catch (ConnectException ce) {
			System.err.println("Error ocurred while trying to connect to the server");
			System.err.flush();
		} catch (Exception e) {
			System.err.println("An unexpected error ocurred while trying to find the contact:");
			System.err.flush();
			e.printStackTrace();
		}
		return contactDTO;
	}

	/**
	 * Retrieves and prints information about all contacts in the database.
	 * 
	 * <p>
	 * This method calls the {@link ApiContactService#obtainAllContacts()} method to
	 * retrieve a list of {@link ContactDTO} objects representing all contacts. It
	 * then prints the information for each contact, including a separator line
	 * between each contact's details. If there are no contacts in the database, it
	 * prints a message indicating the absence of contacts.
	 * 
	 * @see ApiContactService#obtainAllContacts()
	 */
	public void obtainAllContacts() {
		System.out.println("\n-- Printing all contacts --");
		try {
			List<ContactDTO> contacts = apiContactService.obtainAllContacts();
			if (!contacts.isEmpty()) {
				for (ContactDTO contact : contacts) {
					System.out.println(contact);
					System.out.println("-------------------------");
				}
			} else {
				System.out.println("There aren't any contacts in the database");
			}
		} catch (ConnectException ce) {
			System.err.println("Error ocurred while trying to connect to the server");
			System.err.flush();
		} catch (Exception e) {
			System.err.println("An unexpected error ocurred while trying to obtain the contacts:");
			System.err.flush();
			e.printStackTrace();
		}
	}

	/**
	 * Prompts the user to enter information (name, email, reason and message) for
	 * creating a new contact. After collecting the information, it confirms the
	 * creation with the user and calls the API service to create the contact.
	 *
	 * @param sc The scanner object to read user input.
	 * @see ApiContactService#createContact(ContactDTO)
	 * @see Utilities
	 */
	public void createContact(Scanner sc) {
		System.out.println("\n-- Creating contact --");

		String name = Utilities.promptString(sc, "Enter your name: ", false);

		String email = Utilities.promptEmail(sc, "Enter your email: ", false);

		Reason reason = Utilities.promptReason(sc, "What is the reason of the contact?", false);

		String message = Utilities.promptString(sc, "Write the message you want to send us: ", false);

		ContactDTO contactDTO = new ContactDTO(name, email, reason, message);

		if (Utilities.confirm(sc, "\nAre you sure you want to create this contact?")) {
			try {
				apiContactService.createContact(contactDTO);
				System.out.println("Contact created successfully");
			} catch (ConnectException ce) {
				System.err.println("Error ocurred while trying to connect to the server");
				System.err.flush();
			} catch (Exception e) {
				System.err.println("An unexpected error ocurred while trying to create the contact:");
				System.err.flush();
				e.printStackTrace();
			}
		} else {
			System.out.println("Operation cancelled");
		}
	}

	/**
	 * Prompts the user to select a contact for updating and then collects new
	 * information (name, email, reason and message) for the selected contact. After
	 * confirming the changes with the user, it calls the API service to update the
	 * contact.
	 *
	 * @param sc The scanner object to read user input.
	 * @see ApiContactService#updateContact(ContactDTO)
	 * @see Utilities
	 */
	public void updateContact(Scanner sc) {
		System.out.println("\n-- Updating contact --");
		System.out.println("Selecting the contact to be updated");
		ContactDTO contactToUpdate = findContactById(sc);
		if (contactToUpdate != null) {

			System.out.println("-------------------------");

			String name = Utilities.promptString(sc, "Enter the new name (leave blank to keep the old one): ", true);

			String email = Utilities.promptEmail(sc, "Enter the new email (leave blank to keep the old one): ", true);

			Reason reason = Utilities.promptReason(sc, "Select the new reason (leave blank to keep the old one): ",
					true);

			String message = Utilities.promptString(sc, "Enter the new message (leave blank to keep the old one): ",
					true);

			if (!name.equals(""))
				contactToUpdate.setName(name);

			if (!email.equals(""))
				contactToUpdate.setEmail(email);

			if (reason != null)
				contactToUpdate.setReason(reason);

			if (!message.equals(""))
				contactToUpdate.setMessage(message);

			System.out.println("The updated contact will be: ");
			System.out.println(contactToUpdate);
			if (Utilities.confirm(sc, "Are you sure you want to apply the changes?")) {
				try {
					apiContactService.updateContact(contactToUpdate);
					System.out.println("Contact updated successfully");
				} catch (ConnectException ce) {
					System.err.println("Error ocurred while trying to connect to the server");
					System.err.flush();
				} catch (Exception e) {
					System.err.println("An unexpected error ocurred while trying to update the contact:");
					System.err.flush();
					e.printStackTrace();
				}
			} else {
				System.out.println("Operation cancelled");
			}
		}
	}

	/**
	 * Prompts the user to select a contact for deletion and then confirms the
	 * deletion with the user before calling the API service to delete the contact.
	 *
	 * @param sc The scanner object to read user input.
	 * @see ApiContactService#deleteContact(String)
	 * @see Utilities
	 */
	public void deleteContact(Scanner sc) {
		System.out.println("\n-- Deleting contact --");
		System.out.println("Selecting the contact to be deleted");
		ContactDTO contactToDelete = findContactById(sc);

		if (contactToDelete != null) {
			System.out.println("-------------------------");

			if (Utilities.confirm(sc, "Are you sure you want to delete this contact?")) {
				try {
					apiContactService.deleteContact(String.valueOf(contactToDelete.getId()));
					System.out.println("Contact deleted successfully");
				} catch (ConnectException ce) {
					System.err.println("Error ocurred while trying to connect to the server");
					System.err.flush();
				} catch (Exception e) {
					System.err.println("An unexpected error ocurred while trying to delete the contact:");
					System.err.flush();
					e.printStackTrace();
				}
			} else {
				System.out.println("Operation cancelled");
			}
		}
	}

}
