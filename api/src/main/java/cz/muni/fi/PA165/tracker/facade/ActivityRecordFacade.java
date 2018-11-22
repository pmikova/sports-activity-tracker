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

    /**
     * Get all Activity records by a single user.
     *
     * @param id of user whose activities we want displayed
     * @return List of all activity records belonging to the user
     */

    List<ActivityRecordDTO> getAllByUser(Long id);

    /*
    ActivityRecordDTO getById(Long id);
    List<ActivityRecordDTO> getByUser(User user);
    List<ActivityRecordDTO> getAll();

    void getUser();
    void getDistance();
    void getGetUser();
    void getStartTime();
    void getEndTime();

    void updateDistance();
    void updateStartTime();
    void updateEndTime();

    void create();

    void update();

    void delete();
*/

}
