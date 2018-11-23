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

    Long create(BurnedCaloriesCreateDTO burnedCalories);

    void delete(BurnedCaloriesDTO burnedCalories);

    void update(BurnedCaloriesDTO burnedCalories);

    List<BurnedCaloriesDTO> getAll();

    BurnedCaloriesDTO getById(Long id);

    List<BurnedCaloriesDTO> getByUser(UserDTO user);

    List<BurnedCaloriesDTO> getByActivity(ActivityRecordDTO activityRecord);
}
