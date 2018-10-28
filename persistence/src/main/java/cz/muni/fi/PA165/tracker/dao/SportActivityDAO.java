package cz.muni.fi.PA165.tracker.dao;
import cz.muni.fi.PA165.tracker.entities.SportActivity;

import java.util.List;

/**
 * Interface for Sport Activity Data Access Object.
 * @author pmikova 433345
 */
public interface SportActivityDAO {

    /**
     * Creates new activity record.
     * @param activity Activity object to be added to the database
     */
    void create(SportActivity activity);

    /**
     * Get activity by its ID.
     * @param id of the activity
     * @return SportActivity object with given id
     */
    SportActivity getById(Long id);

    /**
     * Get a list of all activities
     * @return list of SportsActivity objects
     */
    List<SportActivity> getAll();

    /**
     * Update activity record.
     * @param activity to update
     */
    void update(SportActivity activity);

    /**
     * Deletes activity record.
     * @param activity
     */
    void delete(SportActivity activity);
}
