package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.entities.User;

import java.util.List;

/**
 * Provides services to work with user.
 * @author pmikova 433345
 */
public interface UserService {

    /**
     * Register User.
     * @param user user to register
     * @param password password of an user
     */
    void register(User user, String password);

    /**
     * Authenticate and log in User.
     * @param user user to log in
     * @param password password of given user
     * @return true if the authentication and log in was successful, false otherwise
     */
    boolean authenticate(User user, String password);

    /**
     * Check if user has administrator status.
     * @param id user to check
     * @return true if user is an admin, false otherwise
     */
    boolean isAdministrator(Long id);

    /**
     * Delete user.
     * @param user user to delete
     */
    void delete(User user);

    /**
     * Find user with given ID.
     * @param id id of user
     * @return User with given id
     */
    User getById(Long id);

    /**
     * Get list of all user
     * @return list of users
     */
    List<User> getAll();

    /**
     * Find user with given email.
     * @param email email of user
     * @return User with given email
     */
    User getByEmail(String email);

    /**
     * Updates user.
     * @param user to update
     * @return updated user.
     */
    User update(User user);

    /**
     * Get age of given user
     * @param user user to get age of
     * @return age of given user
     */
    int getAge(User user);
}
