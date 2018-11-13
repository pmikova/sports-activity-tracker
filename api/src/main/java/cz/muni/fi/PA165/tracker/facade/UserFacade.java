package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.dto.UserAuthenticationDTO;
import cz.muni.fi.PA165.tracker.dto.UserCreateDTO;
import cz.muni.fi.PA165.tracker.dto.UserDTO;

import java.util.List;

/**
 * Facade interface for User.
 * @author pmikova 433345
 */
public interface UserFacade {

    /**
     * Creates user.
     * @param user user to create
     * @return id of created user
     */
    Long create(UserCreateDTO user);

    /**
     * Update user.
     * @param user user to update
     */
    void update(UserDTO user);

    /**
     * Delete user.
     * @param user user to delete
     */
    void delete(UserDTO user);

    /**
     * Get user by given ID.
     * @param id ID of user we want to retrieve
     * @return user with given ID
     */
    UserDTO getById(Long id);

    /**
     * Get all users.
     * @return List of users
     */
    List<UserDTO> getAll();

    /**
     * Get user by given email.
     * @param email email of User we want to retrieve
     * @return
     */
    UserDTO getByEmail(String email);

    /**
     * Logs in an user.
     * @param auth user to log in
     * @return result of logging in (false if failed, true if succeeds
     */
    boolean logIn(UserAuthenticationDTO auth);

    /**
     * Determine if user is admin or not.
     * @param user user to check
     * @return true if user is admin, false otherwise
     */
    boolean isAdministrator(UserDTO user);

}
