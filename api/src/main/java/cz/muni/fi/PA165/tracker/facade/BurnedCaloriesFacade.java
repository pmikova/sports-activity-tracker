package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.dto.ActivityRecordDTO;
import cz.muni.fi.PA165.tracker.dto.BurnedCaloriesCreateDTO;
import cz.muni.fi.PA165.tracker.dto.BurnedCaloriesDTO;
import cz.muni.fi.PA165.tracker.dto.UserDTO;

import java.util.List;

/**
 * Facade interface for Burned Calories.
 * @author HonzaOstrava
 */
public interface BurnedCaloriesFacade {

    /**
     * Creates burnedCalories.
     * @param burnedCalories burnedCalories to create
     * @return id of created user
     */
    Long create(BurnedCaloriesCreateDTO burnedCalories);

    /**
     * Update burnedCalories.
     * @param burnedCalories burnedCalories to update
     */
    void update(BurnedCaloriesDTO burnedCalories);

    /**
     * Delete burnedCalories.
     * @param burnedCalories burnedCalories to delete
     */
    void delete(BurnedCaloriesDTO burnedCalories);

    /**
     * Get all burnedCalories.
     * @return List of burnedCalories
     */
    List<BurnedCaloriesDTO> getAll();

    /**
     * Get burnedCalories by id.
     * @param id id to find by
     * @return wanted burnedCalories
     */
    BurnedCaloriesDTO getById(Long id);

    /**
     * Get burnedCalories by user.
     * @param user user to find by
     * @return List of burnedCalories
     */
    List<BurnedCaloriesDTO> getByUser(UserDTO user);

    /**
     * Get burnedCalories by activityRecord.
     * @param activityRecord activityRecord to find by
     * @return List of burnedCalories
     */
    List<BurnedCaloriesDTO> getByActivity(ActivityRecordDTO activityRecord);
}
