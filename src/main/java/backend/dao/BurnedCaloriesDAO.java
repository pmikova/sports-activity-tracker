package backend.dao;

import backend.ActivityId;
import backend.entities.BurnedCalories;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

/**
 * DAO representing burned calories object database entry
 * @author pmikova
 */
public interface BurnedCaloriesDAO {

    /**
     * Set database connection
     * @param ds data source
     */
    public void setDataSource(DataSource ds);

    /**
     * This method should contain prepared statement for create
     * @param burnedCalories  to replace
     */
    void createBurnedCalories(BurnedCalories burnedCalories);

    /**
     * Update burned calories record.
     * @param burnedCalories burnedCalories object to update
     */
    public void updateBurnedCalories(BurnedCalories burnedCalories);

    /**
     * Deletes burned calories object for given activity.
     * @param activityId id of given activity
     */
    public void deleteBurnedCalories(ActivityId activityId);

    /**
     * This method should contain prepared statement for QUERY
     * @param activity to find burned calories for
     * @return
     */
    public BurnedCalories getBurnedCalories(ActivityId activity);

    /**
     * Get all information about calories burned in various sports as a list
     * @return list of BurnedCalories objects
     */
    public List<BurnedCalories> getBurnedCaloriesTable();

}


