package dev.arack.enlace.iam.application.port.input.services;

import dev.arack.enlace.iam.application.dto.response.UserResponse;
import dev.arack.enlace.iam.domain.aggregates.UserEntity;

import java.util.List;

/**
 * Interface for user management services, providing methods to retrieve, update, and delete user information.
 */
public interface UserService {

    /**
     * Retrieves a list of all users.
     *
     * @return A {@link List} of {@link UserResponse} objects representing all users.
     */
    List<UserResponse> getAllUsers();

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to be retrieved.
     * @return A {@link UserEntity} object representing the user with the specified username.
     */
    UserResponse getUserByUsername(String username);

    /**
     * Updates the information of a user identified by their username.
     *
     * @param username The {@link String} object containing the new user information.
     */
    void updateUsername(String username);

    /**
     * Deletes a user identified by their username.
     *
     */
    void deleteUser();
}
