package com.sitelicon.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitelicon.exceptions.UserNotFound;
import com.sitelicon.model.User;
import com.sitelicon.repository.UserRepository;

/**
 * Service class that handles business logic related to user operations.
 * 
 * @see {@link User}
 * @see {@link UserRepository}
 */
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	/**
	 * Retrieves a list of all users.
	 *
	 * @return List of users.
	 */
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Retrieves a user by their unique identifier.
	 *
	 * @param id The unique identifier of the user.
	 * @return User object if found.
	 * @throws UserNotFound If the user with the specified ID is not found.
	 */
	public User getUserById(Long id) throws UserNotFound {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFound());
	}

	/**
	 * Creates a new user.
	 *
	 * @param user The user object to be created.
	 */
	public void createUser(User user) {
		userRepository.save(user);
	}

	/**
	 * Updates an existing user. Updates all the fields except ID and Timestamp
	 *
	 * @param id          The unique identifier of the user to be updated.
	 * @param updatedUser The updated user object.
	 * @throws UserNotFound If the user with the specified ID is not found.
	 */
	public void updateUser(Long id, User updatedUser) throws UserNotFound {
		User userToUpdate = userRepository.findById(id).orElseThrow(() -> new UserNotFound());

		BeanUtils.copyProperties(updatedUser, userToUpdate, "id", "timestamp");
		userRepository.save(userToUpdate);

	}

	/**
	 * Deletes a user by their unique identifier.
	 *
	 * @param id The unique identifier of the user to be deleted.
	 * @throws UserNotFound If the user with the specified ID is not found.
	 */
	public void deleteUser(Long id) throws UserNotFound {
		userRepository.findById(id).orElseThrow(() -> new UserNotFound());
		userRepository.deleteById(id);
	}
}