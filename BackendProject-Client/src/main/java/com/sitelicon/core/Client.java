package com.sitelicon.core;

import java.util.Scanner;

import com.sitelicon.service.ContactService;
import com.sitelicon.service.UserService;

/**
 * This class prints the main menus of the app and redirects the user to the
 * submenus requested by himself.
 */
public class Client {

	UserService userService = new UserService();
	ContactService contactService = new ContactService();

	/**
	 * Runs the main menu.
	 */
	public void run() {
		mainMenu();
	}

	/**
	 * Uses {@link #printMainMenu()} to display options and redirects the user to
	 * either {@link #usersMenu(Scanner)} or {@link #contactMenu(Scanner)},
	 * depending on the selected option.
	 * 
	 * @see #usersMenu(Scanner)
	 * @see #contactMenu(Scanner)
	 */
	private void mainMenu() {
		String opt = "-1";
		try (Scanner sc = new Scanner(System.in)) {
			while (!opt.equals("0")) {
				printMainMenu();
				opt = sc.nextLine().trim();
				switch (opt) {
				case "0":
					System.out.println("Ending client process");
					break;
				case "1":
					usersMenu(sc);
					break;
				case "2":
					contactMenu(sc);
					break;
				default:
					System.err.println("The option typed is not valid");
					System.err.flush();
				}
			}
		}
	}

	/**
	 * Uses {@link #printUsersMenu()} to print the options for the users menu, and
	 * then redirects the user to the respective methods of the class
	 * {@link UserService}
	 * 
	 * @param sc A Scanner object for reading user input.
	 * @see #printUsersMenu()
	 * @see UserService#findUserById(Scanner)
	 * @see UserService#obtainAllUsers()
	 * @see UserService#createUser(Scanner)
	 * @see UserService#updateUser(Scanner)
	 * @see UserService#deleteUser(Scanner)
	 * 
	 */
	private void usersMenu(Scanner sc) {
		String opt = "-1";
		while (!opt.equals("0")) {
			printUsersMenu();
			opt = sc.nextLine().trim();
			switch (opt) {
			case "0":
				System.out.println("Exiting users menu");
				break;
			case "1":
				userService.findUserById(sc);
				break;
			case "2":
				userService.obtainAllUsers();
				break;
			case "3":
				userService.createUser(sc);
				break;
			case "4":
				userService.updateUser(sc);
				break;
			case "5":
				userService.deleteUser(sc);
				break;
			default:
				System.err.println("The option typed is not valid");
				System.err.flush();
			}
		}
	}

	/**
	 * Uses {@link #printContactMenu()} to print the options for the contact menu,
	 * and then redirects the user to the respective methods of the class
	 * {@link ContactService}
	 * 
	 * @param sc A Scanner object for reading user input.
	 * @see #printContactMenu()
	 * @see ContactService#findContactById(Scanner)
	 * @see ContactService#obtainAllContacts()
	 * @see ContactService#createContact(Scanner)
	 * @see ContactService#updateContact(Scanner)
	 * @see ContactService#deleteContact(Scanner)
	 */
	private void contactMenu(Scanner sc) {
		String opt = "-1";
		while (!opt.equals("0")) {
			printContactMenu();
			opt = sc.nextLine().trim();
			switch (opt) {
			case "0":
				System.out.println("Exiting contact menu");
				break;
			case "1":
				contactService.findContactById(sc);
				break;
			case "2":
				contactService.obtainAllContacts();
				break;
			case "3":
				contactService.createContact(sc);
				break;
			case "4":
				contactService.updateContact(sc);
				break;
			case "5":
				contactService.deleteContact(sc);
				break;
			default:
				System.err.println("The option typed is not valid");
				System.err.flush();
			}
		}
	}

	/**
	 * Displays the main menu with options to exit, access the Users CRUD menu, or
	 * access the Contact CRUD menu.
	 */
	private void printMainMenu() {
		System.out.println("\n--- Main Menu ---");
		System.out.println("0. Exit");
		System.out.println("1. Access Users CRUD");
		System.out.println("2. Access Contact CRUD");
		System.out.print("Type the number of an option: ");
	}

	/**
	 * Displays the users menu with options to exit to the main menu, obtain a user
	 * by ID, obtain all users, create a new user, update an existing user, or
	 * delete a user.
	 */
	private void printUsersMenu() {
		System.out.println("\n--- Users Menu ---");
		System.out.println("0. Exit to main menu");
		System.out.println("1. Obtain user by ID");
		System.out.println("2. Obtain all users");
		System.out.println("3. Create user");
		System.out.println("4. Update user");
		System.out.println("5. Delete user");
		System.out.print("Type the number of an option: ");
	}

	/**
	 * Displays the contacs menu with options to exit to the main menu, obtain a
	 * contact by ID, obtain all contacts, create a new contact, update an existing
	 * contact, or delete a contact.
	 */
	private void printContactMenu() {
		System.out.println("\n--- Contact Menu ---");
		System.out.println("0. Exit to main menu");
		System.out.println("1. Obtain contact by ID");
		System.out.println("2. Obtain all contacts");
		System.out.println("3. Create contact");
		System.out.println("4. Update contact");
		System.out.println("5. Delete contact");
		System.out.print("Type the number of an option: ");
	}
}
