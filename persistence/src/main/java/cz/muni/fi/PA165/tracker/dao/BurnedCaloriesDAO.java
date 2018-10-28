package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.entities.BurnedCalories;

import java.util.List;

/**
 * Interface for Burned Calories Data Access Object.
 * @author pmikova 433345
 */
//TODO: This should be further discussed whether we dont want to link it with activity object?
public interface BurnedCaloriesDAO {

    /**
     * Creates new BurnedCalories record.
     * @param calories BurnedCalories object to be added to the database
     */
    void create(BurnedCalories calories);

    /**
     * Get burned calories by its ID.
     * @param id of the calories object
     * @return BurnedCalories object with given id
     */
    BurnedCalories getById(Long id);

    /**
     * Get a list of all BurnedCalories objects.
     * @return list of BurnedCalories objects
     */
    List<BurnedCalories> getAll();

    /**
     * Update burnedCalories record.
     * @param calories BurnedCalories to update
     */
    void update(BurnedCalories calories);

    /**
     * Deletes BurnedCalories record.
     * @param calories BurnedCalories to delete
     */
    void delete(BurnedCalories calories);

}


