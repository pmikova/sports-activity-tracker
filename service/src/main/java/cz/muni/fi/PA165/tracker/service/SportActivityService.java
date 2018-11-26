package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.entities.SportActivity;

import java.util.List;

/**
 * Service for Sport Activity.
 * @author Dominik-Bujna
 */

public interface SportActivityService {

    /**
     * Create a record of an activity.
     *
     * @param sportActivity to create
     */
    void create(SportActivity sportActivity);

    /**
     * Update SportActivity.
     *
     * @param sportActivity to update
     */
    void update(SportActivity sportActivity);

    /**
     * Delete SportActivity.
     *
     * @param sportActivity to delete
     */
    void delete(SportActivity sportActivity);

    /**
     * Get ActivityRecord by given ID.
     *
     * @param id ID of sport activity we want to retrieve
     * @return SportActivity with given ID
     */
    SportActivity getById(Long id);

    /**
     * Get all Activity records by all users.
     *
     * @return List of all activity records
     */

    List<SportActivity> getAll();
}
