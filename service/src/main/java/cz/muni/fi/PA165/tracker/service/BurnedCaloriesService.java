package cz.muni.fi.PA165.tracker.service;

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
     * @return updated burnedCalories.
     */
    BurnedCalories update(BurnedCalories burnedCalories);

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
     * @return list of burnedCalories
     */
    List<BurnedCalories> getByUser();

    /**
     * Get list of burnedCalories by activityRecord
     * @return list of burnedCalories
     */
    List<BurnedCalories> getByActivity();
}
