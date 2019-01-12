package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.BurnedCalories;
import cz.muni.fi.PA165.tracker.entities.User;

import java.util.List;

/**
 * Interface for Burned Calories Data Access Object.
 * @author pmikova 433345
 */
public interface BurnedCaloriesDAO {

    /**
     * Creates new BurnedCalories record.
     *
     * @param calories BurnedCalories object to be added to the database
     */
    void create(BurnedCalories calories);

    /**
     * Get burned calories by its ID.
     *
     * @param id of the calories object
     * @return BurnedCalories object with given id
     */
    BurnedCalories getById(Long id);

    /**
     * Get a list of all BurnedCalories objects.
     *
     * @return list of BurnedCalories objects
     */
    List<BurnedCalories> getAll();

    /**
     * Update burnedCalories record.
     *
     * @param calories BurnedCalories to update
     */
    void update(BurnedCalories calories);

    /**
     * Deletes BurnedCalories record.
     *
     * @param calories BurnedCalories to delete
     */
    void delete(BurnedCalories calories);

    /**
     * Deletes all Burned calories connected with user
     *
     * @param user user to delete burned calories for
     */
    void deleteByUser(User user);

    /**
     * Get a list of BurnedCalories objects by User.
     *
     * @param user user to find by
     * @return list of BurnedCalories objects
     */
    List<BurnedCalories> getByUser(User user);

    /**
     * Get a list of BurnedCalories objects by Activity type.
     *
     * @param activityRecordId to find by
     * @return list of BurnedCalories objects
     */
    BurnedCalories getByActivityRecordId(Long activityRecordId);


}


