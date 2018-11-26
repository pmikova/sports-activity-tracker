package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.BurnedCalories;
import cz.muni.fi.PA165.tracker.entities.User;

import java.util.List;

/**
 * Provides services to work with burnedCalories.
 * @author HonzaOstrava
 */
public interface BurnedCaloriesService {

    /**
     * Creates burnedCalories.
     * @param burnedCalories to create
     */
    void create(BurnedCalories burnedCalories);

    /**
     * Updates burnedCalories.
     * @param burnedCalories to update
     */
    void update(BurnedCalories burnedCalories);

    /**
     * Delete burnedCalories.
     * @param burnedCalories user to delete
     */
    void delete(BurnedCalories burnedCalories);

    /**
     * Find burnedCalories with given ID.
     * @param id id of burnedCalories
     * @return BurnedCalories with given id
     */
    BurnedCalories getById(Long id);

    /**
     * Get list of all burnedCalories
     * @return list of burnedCalories
     */
    List<BurnedCalories> getAll();

    /**
     * Get list of burnedCalories by user
     * @param user user to find by
     * @return list of burnedCalories
     */
    List<BurnedCalories> getByUser(User user);

    /**
     * Get list of burnedCalories by activityRecord
     * @param activityRecord activityRecord to find by
     * @return list of burnedCalories
     */
    List<BurnedCalories> getByActivity(ActivityRecord activityRecord);

    /**
     * Computes amount of burned calories and stores it in given BurnedCalories entity
     * @param burnedCalories entity for which we want to compute burned calories
     */
    void computeBurnedCalories(BurnedCalories burnedCalories);
}
