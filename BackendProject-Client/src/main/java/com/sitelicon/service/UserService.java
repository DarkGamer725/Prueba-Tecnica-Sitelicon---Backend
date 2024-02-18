package com.sitelicon.service;

import java.net.ConnectException;
import java.util.List;
import java.util.Scanner;

import com.sitelicon.api.ApiUserService;
import com.sitelicon.dto.UserDTO;
import com.sitelicon.exceptions.NotFoundException;
import com.sitelicon.util.Utilities;

/**
 * Service class responsible for handling user-related operations by interacting
 * with the API through {@link ApiUserService}.
 * <p>
 * This class provides methods for finding, creating, updating, and deleting
 * users. It communicates with the API service and handles user input and
 * exceptions that may occur during these operations.
 *
 * @see ApiUserService
 * @see UserDTO
 * @see NotFoundException
 * @see ConnectException
 * @see Utilities
 */
public class UserService {
	private final ApiUserService apiUserService = new ApiUserService();

	/**
	 * Asks the user for a numeric ID, retrieves and prints information about the
	 * user found, or displays a message if the user wasn't found.
	 * 
	 * @param sc A Scanner object for reading user input.
	 * @return The {@link UserDTO} found. Null if it wasn't found.
	 * @see ApiUserService#findUserById(int)
	 */
	public UserDTO findUserById(Scanner sc) {
		System.out.println("\n-- Finding User --");
		long id = Utilities.promptLong(sc, "Enter the user's Id: ");
		UserDTO userDTO = null;
		try {
			userDTO = apiUserService.findUserById(id);
			System.out.println("- User found -\n" + userDTO);
		} catch (NotFoundException e) {
			System.out.println("Couldn't find the user by id: " + id);
		} catch (ConnectException ce) {
			System.err.println("Error ocurred while trying to connect to the server");
			System.err.flush();
		} catch (Exception e) {
			System.err.println("An unexpected error ocurred while trying to find the user:");
			System.err.flush();
			e.printStackTrace();
		}
		return userDTO;
	}

	/**
	 * Retrieves and prints information about all users in the database.
	 * 
	 * <p>
	 * This method calls the {@link ApiUserService#obtainAllUsers()} method to
	 * retrieve a list of {@link UserDTO} objects representing all users. It then
	 * prints the information for each user, including a separator line between each
	 * user's details. If there are no users in the database, it prints a message
	 * indicating the absence of users.
	 * 
	 * @see ApiUserService#obtainAllUsers()
	 */
	public void obtainAllUsers() {
		System.out.println("\n-- Printing all users --");
		try {
			List<UserDTO> users = apiUserService.obtainAllUsers();
			if (!users.isEmpty()) {
				for (UserDTO user : users) {
					System.out.println(user);
					System.out.println("-------------------------");
				}
			} else {
				System.out.println("There aren't any users in the database");
			}

		} catch (ConnectException ce) {
			System.err.println("Error ocurred while trying to connect to the server");
			System.err.flush();
		} catch (Exception e) {
			System.err.println("An unexpected error ocurred while trying to obtain the users:");
			System.err.flush();
			e.printStackTrace();
		}
	}

	/**
	 * Prompts the user to enter information (name, last name, phone number, email,
	 * and password) for creating a new user. After collecting the information, it
	 * confirms the creation with the user and calls the API service to create the
	 * user.
	 *
	 * @param sc The scanner object to read user input.
	 * @see ApiUserService#createUser(UserDTO)
	 * @see Utilities
	 */
	public void createUser(Scanner sc) {
		System.out.println("\n-- Creating user --");

		String name = Utilities.promptString(sc, "Enter user's name: ", false);

		String lastName = Utilities.promptString(sc, "Enter user's last name: ", false);

		String number = Utilities.promptPhoneNumber(sc, "Enter the phone number: ", false);

		String email = Utilities.promptEmail(sc, "Enter the email: ", false);

		String password = Utilities.promptPassword(sc, "Enter the password: ", false);

		UserDTO userDTO = new UserDTO(name, lastName, number, email, password);

		if (Utilities.confirm(sc, "\nAre you sure you want to create this user?")) {
			try {
				apiUserService.createUser(userDTO);
				System.out.println("User created successfully");
			} catch (ConnectException ce) {
				System.err.println("Error ocurred while trying to connect to the server");
				System.err.flush();
			} catch (Exception e) {
				System.err.println("An unexpected error ocurred while trying to create the user:");
				System.err.flush();
				e.printStackTrace();
			}
		} else {
			System.out.println("Operation cancelled");
		}
	}

	/**
	 * Prompts the user to select a user for updating and then collects new
	 * information (name, last name, phone number, email, and password) for the
	 * selected user. After confirming the changes with the user, it calls the API
	 * service to update the user.
	 *
	 * @param sc The scanner object to read user input.
	 * @see ApiUserService#updateUser(UserDTO)
	 * @see Utilities
	 */
	public void updateUser(Scanner sc) {
		System.out.println("\n-- Updating user --");
		System.out.println("Selecting the user to be updated");
		UserDTO userToUpdate = findUserById(sc);

		if (userToUpdate != null) {
			System.out.println("-------------------------");

			String name = Utilities.promptString(sc, "Enter the new name (leave blank to keep the old one): ", true);

			String lastName = Utilities.promptString(sc, "Enter the new last name (leave blank to keep the old one): ",
					true);

			String number = Utilities.promptPhoneNumber(sc,
					"Enter the new phone number (leave blank to keep the old one): ", true);

			String email = Utilities.promptEmail(sc, "Enter the new email (leave blank to keep the old one): ", true);

			String password = Utilities.promptPassword(sc, "Enter the new password (leave blank to keep the old one): ",
					true);

			if (!name.equals(""))
				userToUpdate.setName(name);

			if (!lastName.equals(""))
				userToUpdate.setLastName(lastName);

			if (!number.equals(""))
				userToUpdate.setPhoneNumber(number);

			if (!email.equals(""))
				userToUpdate.setEmail(email);

			if (!password.equals(""))
				userToUpdate.setPassword(password);

			System.out.println("The updated user will be: ");
			System.out.println(userToUpdate);
			if (Utilities.confirm(sc, "Are you sure you want to apply the changes?")) {
				try {
					apiUserService.updateUser(userToUpdate);
					System.out.println("User updated successfully");
				} catch (ConnectException ce) {
					System.err.println("Error ocurred while trying to connect to the server");
					System.err.flush();
				} catch (Exception e) {
					System.err.println("An unexpected error ocurred while trying to update the user:");
					System.err.flush();
					e.printStackTrace();
				}
			} else {
				System.out.println("Operation cancelled");
			}
		}
	}

	/**
	 * Prompts the user to select a user for deletion and then confirms the deletion
	 * with the user before calling the API service to delete the user.
	 *
	 * @param sc The scanner object to read user input.
	 * @see ApiUserService#deleteUser(String)
	 * @see Utilities
	 */
	public void deleteUser(Scanner sc) {
		System.out.println("\n-- Deleting user --");
		System.out.println("Selecting the user to be deleted");
		UserDTO userToDelete = findUserById(sc);

		if (userToDelete != null) {
			System.out.println("-------------------------");

			if (Utilities.confirm(sc, "Are you sure you want to delete this user?")) {
				try {
					apiUserService.deleteUser(userToDelete.getId());
					System.out.println("User deleted successfully");
				} catch (ConnectException ce) {
					System.err.println("Error ocurred while trying to connect to the server");
					System.err.flush();
				} catch (Exception e) {
					System.err.println("An unexpected error ocurred while trying to delete the user:");
					System.err.flush();
					e.printStackTrace();
				}
			} else {
				System.out.println("Operation cancelled");
			}
		}

	}

}
