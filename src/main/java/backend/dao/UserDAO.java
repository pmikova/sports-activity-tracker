package backend.dao;

import backend.UserId;
import backend.entities.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

/**
 * DAO interface for User entity
 * @author pmikova
 */
public interface UserDAO {

     /**
     * Set database connection
     * @param ds data source
     */
    public void setDataSource(DataSource ds);

    /**
     * This method should contain prepared statement for create
     * @param user user to add to the database
     */
    public void createUser (User user);

    /**
     * Update user record, this method should contain prepared statement for UPDATE
     * @param user User object to replace
     */
    public void updateUser(User user);

    /**
     * Deletes user record according to given ID.
     * @param id UUID id of given user
     */
    public void deleteUser(UUID id);

    /**
     * This method should contain prepared statement for QUERY
     * @param user to find in the database
     * @return
     */
    public User getUser(String email);

    /**
     * Get a list of all users
     * @return list of BurnedCalories objects
     */
    public List<User> getUserTable();

}
