package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.User;

import java.time.Duration;
import java.util.List;

/**
 * Provides services to work with an activity record.
 * @author Dominik-Bujna
 */
public interface ActivityRecordService {

    /**
     * Calculate the duration of the activity
     *
     * @param activityRecord activityRecord to calculate the duration of
     */
    Duration calculateDuration(ActivityRecord activityRecord);


    /**
     * Calculate the speed of the activity
     *
     * @param activityRecord activityRecord to calculate the speed of
     */
    double calculateAverageSpeed(ActivityRecord activityRecord);


    /**
     * Create a record of an activity.
     *
     * @param activityRecord activityRecord to create
     */
    void create(ActivityRecord activityRecord);

    /**
     * Update ActivityRecord.
     *
     * @param activityRecord to update
     */
    void update(ActivityRecord activityRecord);

    /**
     * Delete ActivityRecord.
     *
     * @param activityRecord to delete
     */
    void delete(ActivityRecord activityRecord);

    /**
     * Get ActivityRecord by given ID.
     *
     * @param id ID of activity record we want to retrieve
     * @return ActivityRecord with given ID
     */
    ActivityRecord getById(Long id);

    /**
     * Get all Activity records by all users.
     *
     * @return List of all activity records
     */

    List<ActivityRecord> getAll();

    List<ActivityRecord> getByUser(User user);
}
