package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.dto.ActivityRecordDTO;
import java.util.List;

/**
 * Facade interface for Activity Record.
 * @author Dominikcz/muni/fi/PA165/tracker/facade/ActivityRecordFacade.java-Bujna
 */
public interface ActivityRecordFacade {

    /**
     * Create a record of an activity.
     *
     * @param activityRecordDTO activityRecordDTO to create
     * @return id of created user
     */
    Long create(ActivityRecordDTO activityRecordDTO);

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
     * @param id ID of user we want to retrieve
     * @return user with given ID
     */
    ActivityRecordDTO getById(Long id);

    /**
     * Get all Activities.
     *
     * @return List of users
     */
    List<ActivityRecordDTO> getAll();
}
