package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;

import java.util.List;

/**
 * Interface for Activity Record Data Access Object.
 * @author Dominik-Bujna
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

    List<ActivityRecord> getByUser(User user);

    List<ActivityRecord> getByActivity(SportActivity activity);

    void deleteByUser(User user);
}
