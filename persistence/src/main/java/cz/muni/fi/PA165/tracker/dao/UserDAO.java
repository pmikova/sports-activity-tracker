package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.entities.User;

import java.util.List;

/**
 * Interface for User Data Access Object.
 * @author pmikova
 */
public interface UserDAO {

    /**
     * Creates new user record.
     * @param user object to be added to the database
     */
    void create(User user);

    /**
     * Get user by its ID.
     * @param id of the user
     * @return User object with given id
     */
    User getById(Long id);

    /**
     * Get a user record by email.
     * @param email email of requested user
     * @return User associated with given email
     */
    User getByEmail(String email);

    /**
     * Get a list of all users.
     * @return list of User objects
     */
    List<User> getAll();

    /**
     * Update user record.
     * @param user to update
     */
    void update(User user);

    /**
     * Deletes user record.
     * @param user to delete
     */
    void delete(User user);

}
