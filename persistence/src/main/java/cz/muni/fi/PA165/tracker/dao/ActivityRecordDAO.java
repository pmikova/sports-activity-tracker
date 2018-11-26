package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;

import java.util.List;

/**
 * Interface for Activity Record Data Access Object.
 * @author Dominik-Bujna, pmikova
 */
public interface ActivityRecordDAO {


    /**
     * Create a new ActivityRecord record.
     * @param activityRecord ActivityRecord to be added to the database
     */
    void create(ActivityRecord activityRecord);

    /**
     * Retrieve an ActivityRecord from the database by its id.
     * @param id of the item to be retrieved
     * @return ActivityRecord object with given id
     */

    ActivityRecord getById(Long id);

    /**
     * Retrieve all of the ActivityRecord objects.
     * @return List of all ActivityRecord objects in the database
     */

    List<ActivityRecord> getAll();

    /**
     * Update ActivityRecord record in the database.
     * @param activityRecord ActivityRecord to update
     * @throws IllegalArgumentException
     */

    void update(ActivityRecord activityRecord) throws IllegalArgumentException;

    /**
     * Deletes ActivityRecord record from the database.
     * @param activityRecord ActivityRecord to delete
     */

    void delete(ActivityRecord activityRecord);

    /**
     * Get all ActivityRecords with given user.
     * @param user user to get records for
     * @return list of activity records of user
     */
    List<ActivityRecord> getByUser(User user);

    /**
     * Get records with specified activity.
     * @param activity to get records for
     * @return list of records with given activity
     */
    List<ActivityRecord> getByActivity(SportActivity activity);

    /**
     * Deletes all records connected with given user.
     * @param user user to delete records for.
     */
    void deleteByUser(User user);
}
