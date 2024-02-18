package com.sitelicon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sitelicon.model.User;

/**
 * Repository interface for managing {@link User} entities. Extends
 * {@link JpaRepository} to inherit basic CRUD operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
