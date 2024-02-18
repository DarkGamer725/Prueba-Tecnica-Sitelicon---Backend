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

import com.sitelicon.exceptions.UserNotFound;
import com.sitelicon.model.User;
import com.sitelicon.service.UserService;

/**
 * Controller class for handling HTTP requests related to user operations.
 * 
 * @see {@link User}
 * @see {@link UserService}
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Retrieves a list of all users.
	 *
	 * @return ResponseEntity with a list of users if successful, or an empty list
	 *         if no users are found.
	 */
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	/**
	 * Retrieves a user by their unique identifier.
	 *
	 * @param id The unique identifier of the user.
	 * @return ResponseEntity with the user if found, or a 404 Not Found response if
	 *         the user does not exist.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = null;
		try {
			user = userService.getUserById(id);
			return ResponseEntity.ok(user);
		} catch (UserNotFound e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Creates a new user.
	 *
	 * @param user The user object to be created.
	 * @return ResponseEntity with a 201 Created status if successful.
	 */
	@PostMapping
	public ResponseEntity<Void> createUser(@RequestBody User user) {
		userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * Updates an existing user.
	 *
	 * @param id          The unique identifier of the user to be updated.
	 * @param updatedUser The updated user object.
	 * @return ResponseEntity with a 200 OK status if successful, or a 404 Not Found
	 *         response if the user does not exist.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
		try {
			userService.updateUser(id, updatedUser);
			return ResponseEntity.ok().build();
		} catch (UserNotFound e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Deletes a user by their unique identifier.
	 *
	 * @param id The unique identifier of the user to be deleted.
	 * @return ResponseEntity with a 200 OK status if successful, or a 404 Not Found
	 *         response if the user does not exist.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);
			return ResponseEntity.ok().build();
		} catch (UserNotFound e) {
			return ResponseEntity.notFound().build();
		}
	}
}
