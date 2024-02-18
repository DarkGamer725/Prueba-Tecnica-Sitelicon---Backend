package com.sitelicon.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import com.sitelicon.dto.Reason;

/**
 * The {@code Utilities} class provides utility methods for handling user input,
 * including methods for prompting the user for various types of information
 * such as phone numbers, email addresses, numbers, strings, passwords, reasons,
 * and confirmation messages.
 *
 * <p>
 * The utility methods include:
 * <ul>
 * <li>{@link #promptPhoneNumber(Scanner, String, boolean)}: Prompts the user
 * for a phone number and validates the format.</li>
 * <li>{@link #promptEmail(Scanner, String, boolean)}: Prompts the user for an
 * email address and validates the format.</li>
 * <li>{@link #promptLong(Scanner, String)}: Prompts the user for a long number
 * and validates the format.</li>
 * <li>{@link #promptString(Scanner, String, boolean)}: Prompts the user for a
 * string and validates the format.</li>
 * <li>{@link #promptPassword(Scanner, String, boolean)}: Prompts the user for a
 * password, hashes it, and validates the format.</li>
 * <li>{@link #promptReason(Scanner, String, boolean)}: Prompts the user to
 * select a reason and validates the input.</li>
 * <li>{@link #confirm(Scanner, String)}: Prompts the user for confirmation and
 * validates the input (Y/N).</li>
 * </ul>
 * Additionally, the class contains a private method
 * {@link #hashPassword(String)} to hash passwords using SHA-256.
 *
 * @see Scanner
 * @see Reason
 */
public class Utilities {
	/**
	 * Prompts the user for a phone number and validates the format. The input must
	 * be 9 digits spaced in any way, or blank if allowBlank is true.
	 *
	 * @param sc         The Scanner object for reading user input.
	 * @param message    The message to display to the user.
	 * @param allowBlank Whether a blank input is allowed.
	 * @return The cleaned phone number with a simple format of 9 consecutive
	 *         digits. A blank String if the user's input is blank and allowBlank is
	 *         {@code true}.
	 */
	public static String promptPhoneNumber(Scanner sc, String message, boolean allowBlank) {
		System.out.println(message);
		String cleanedPhoneNumber = "";
		boolean valid = false;
		do {
			String input = sc.nextLine().trim();

			if (input.matches("^(\\s*[0-9]\\s*){9}$") || (allowBlank && input.equals(""))) {
				cleanedPhoneNumber = input.replaceAll("\\D", "");
				valid = true;
			} else {
				System.err.println("Format not valid, please enter the phone number again");
				System.err.flush();
			}
		} while (!valid);
		return cleanedPhoneNumber;
	}

	/**
	 * Prompts the user for an email address and validates the format. The proper
	 * format expected is username@domain.extension, being x@x.xx the smallest
	 * possible valid format, as there aren't any extension shorter than 2
	 * characters.
	 *
	 * @param sc         The Scanner object for reading user input.
	 * @param message    The message to display to the user.
	 * @param allowBlank Whether a blank input is allowed.
	 * @return The validated email address. A blank String if the user's input is
	 *         blank and allowBlank is {@code true}.
	 */
	public static String promptEmail(Scanner sc, String message, boolean allowBlank) {
		System.out.println(message);
		boolean valid = false;
		String retEmail = "";
		do {
			String input = sc.nextLine().trim();
			if (input.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
					|| (allowBlank && input.equals(""))) {
				retEmail = input;
				valid = true;
			} else {
				System.err.println("Format not valid, please enter the email again");
				System.err.flush();
			}
		} while (!valid);
		return retEmail;
	}

	/**
	 * Prompts the user for a long number and validates the format.
	 *
	 * @param sc      The Scanner object for reading user input.
	 * @param message The message to display to the user.
	 * @return The validated long number.
	 */
	public static long promptLong(Scanner sc, String message) {
		System.out.println(message);
		long retLong = 0;
		boolean valid = false;
		do {
			String input = sc.nextLine().trim();

			if (input.matches("^[0-9]+$")) {
				retLong = Long.valueOf(input);
				valid = true;
			} else {
				System.err.println("Format not valid, please enter the number again");
				System.err.flush();
			}
		} while (!valid);
		return retLong;
	}

	/**
	 * Prompts the user for a string and validates the format. If allowBlank is
	 * {@code false}, the input must be at least 1 character long.
	 *
	 * @param sc         The Scanner object for reading user input.
	 * @param message    The message to display to the user.
	 * @param allowBlank Whether a blank input is allowed.
	 * @return The validated string. A blank String if the user's input is blank and
	 *         allowBlank is {@code true}.
	 */
	public static String promptString(Scanner sc, String message, boolean allowBlank) {
		System.out.println(message);
		boolean valid = false;
		String retString = "";
		do {
			String input = sc.nextLine().trim();
			if (!input.equals("") || (allowBlank && input.equals(""))) {
				retString = input;
				valid = true;
			} else {
				System.err.println("Must introduce at least 1 character");
				System.err.flush();
			}
		} while (!valid);
		return retString;
	}

	/**
	 * Prompts the user for a password, hashes it, and validates the format. If
	 * allowBlank is {@code true} and the user inputs a blank string, nothing will
	 * be hashed and this method will return a blank string instead of a hash.
	 *
	 * @param sc         The Scanner object for reading user input.
	 * @param message    The message to display to the user.
	 * @param allowBlank Whether a blank input is allowed.
	 * @return The hashed password. A blank String if the user's input is blank and
	 *         allowBlank is {@code true}.
	 */
	public static String promptPassword(Scanner sc, String message, boolean allowBlank) {
		System.out.println(message);
		boolean valid = false;
		String retPassword = "";
		do {
			String input = sc.nextLine().trim();
			if (!input.equals("")) {
				retPassword = hashPassword(input);
				valid = true;
			} else if (allowBlank && input.equals("")) {
				retPassword = input;
				valid = true;
			} else {
				System.err.println("Must introduce at least 1 character");
				System.err.flush();
			}

		} while (!valid);

		return retPassword;
	}

	/**
	 * Prompts the user to select a reason and validates the input.
	 *
	 * @param sc         The Scanner object for reading user input.
	 * @param message    The message to display to the user.
	 * @param allowBlank Whether a blank input is allowed.
	 * @return The selected {@link Reason}. Null if the user's input is blank and
	 *         allowBlank is {@code true}.
	 */
	public static Reason promptReason(Scanner sc, String message, boolean allowBlank) {
		Reason reason = null;
		String opt = "-1";
		boolean reasonSelected = false;
		while (!reasonSelected) {
			System.out.println(message);
			System.out.println("1. Question");
			System.out.println("2. Information");
			System.out.println("3. Alert");
			System.out.print("Type the number of an option: ");
			opt = sc.nextLine().trim();

			switch (opt) {
			case "1":
				reason = Reason.QUESTION;
				reasonSelected = true;
				System.out.println("Reason selected: Question\n");
				break;
			case "2":
				reason = Reason.INFORMATION;
				reasonSelected = true;
				System.out.println("Reason selected: Information\n");
				break;
			case "3":
				reason = Reason.ALERT;
				reasonSelected = true;
				System.out.println("Reason selected: Alert\n");
				break;
			default:
				if (allowBlank && opt.equals("")) {
					reasonSelected = true;
					System.out.println("Reason selected: Keep the old one\n");
				} else {
					System.err.println("The option typed is not valid\n");
					System.err.flush();
				}
			}
		}
		return reason;
	}

	/**
	 * Prompts the user for confirmation and validates the input (Y/N). Every input
	 * starting by "Y" or "y" is considered as a yes, and will return {@code true},
	 * if it starts with "N" or "n" is considered a no and will return {@code false}
	 *
	 * @param sc      The Scanner object for reading user input.
	 * @param message The confirmation message to display to the user.
	 * @return {@code true} if the user confirms with 'Y' or 'y', {@code false}
	 *         otherwise.
	 */
	public static boolean confirm(Scanner sc, String message) {
		System.out.println(message + " (Y/N)");
		boolean valid = false;
		boolean ret = false;
		do {
			String opt = sc.nextLine().trim();
			if (opt.startsWith("Y") || opt.startsWith("y")) {
				ret = true;
				valid = true;
			} else if (opt.startsWith("N") || opt.startsWith("n")) {
				ret = false;
				valid = true;
			} else {
				System.err.println("The option typed is not valid\n");
				System.err.flush();
			}
		} while (!valid);
		return ret;
	}

	/**
	 * Hashes the provided password using the SHA-256 algorithm.
	 *
	 * @param password The password to hash.
	 * @return The hashed password in a String format.
	 */
	private static String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = md.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();

			for (byte b : hashedBytes) {
				sb.append(String.format("%02x", b));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error hashing password", e);
		}
	}
}
