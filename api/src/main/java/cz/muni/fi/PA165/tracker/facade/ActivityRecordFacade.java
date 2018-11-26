package cz.muni.fi.PA165.tracker.facade;
import cz.muni.fi.PA165.tracker.dto.ActivityRecordCreateDTO;
import cz.muni.fi.PA165.tracker.dto.ActivityRecordDTO;
import java.util.List;

/**
 * Facade interface for Activity Record.
 * @author Dominik-Bujna
 */
public interface ActivityRecordFacade {

    /**
     * Create a record of an activity.
     *
     * @param activityRecordDTO activityRecordDTO to create
     * @return id of created user
     */
    void create(ActivityRecordCreateDTO activityRecordDTO);

    /**
     * Update ActivityRecord.
     *
     * @param activityRecordDTO to update
     */
    void update(ActivityRecordDTO activityRecordDTO);

    /**
     * Delete ActivityRecord.
     *
     * @param activityRecordDTO to delete
     */
    void delete(ActivityRecordDTO activityRecordDTO);

    /**
     * Get ActivityRecord by given ID.
     *
     * @param id ID of activity record we want to retrieve
     * @return ActivityRecord with given ID
     */
    ActivityRecordDTO getById(Long id);

    /**
     * Get all Activity records by all users.
     *
     * @return List of all activity records
     */

    List<ActivityRecordDTO> getAll();
}
