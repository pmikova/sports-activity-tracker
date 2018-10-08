package backend.dao;
import backend.ActivityId;
import backend.entities.SportActivity;
import backend.entities.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

/**
 * Interface for Sport Activity Data Access Object.
 * @author pmikova
 */
public interface SportActivityDAO {

    /**
     * Set database connection
     * @param ds data source
     */
    public void setDataSource(DataSource ds);

    /**
     * This method should contain prepared statement for create
     * @param activity Activity object to be added to the database
     */
    void create(SportActivity activity);

    /**
     * This method should contain prepared statement for QUERY
     * @param activity to find in the database
     * @return
     */
    public ActivityId getSportsActivity(ActivityId activity);

    /**
     * Get a list of all users
     * @return list of BurnedCalories objects
     */
    public List<User> getUserTable();

    /**
     * Update user record, this method should contain prepared statement for UPDATE
     * @param user User object to replace
     */
    public void updateUser(User user);

    /**
     * Deletes user record according to given ID.
     * @param id UUID id of given user
     */
    public void delete(UUID id);
}
