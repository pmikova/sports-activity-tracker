package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.dto.ActivityRecordDTO;
import cz.muni.fi.PA165.tracker.dto.BurnedCaloriesCreateDTO;
import cz.muni.fi.PA165.tracker.dto.BurnedCaloriesDTO;
import cz.muni.fi.PA165.tracker.dto.UserDTO;

import java.util.List;

/**
 * Burned Calories Facade implementation.
 * @author HonzaOstrava
 */
public class BurnedCaloriesFacadeImpl implements BurnedCaloriesFacade {
    @Override
    public Long create(BurnedCaloriesCreateDTO burnedCalories) {
        return null;
    }

    @Override
    public void delete(BurnedCaloriesDTO burnedCalories) {

    }

    @Override
    public void update(BurnedCaloriesDTO burnedCalories) {

    }

    @Override
    public List<BurnedCaloriesDTO> getAll() {
        return null;
    }

    @Override
    public BurnedCaloriesDTO getById(Long id) {
        return null;
    }

    @Override
    public List<BurnedCaloriesDTO> getByUser(UserDTO user) {
        return null;
    }

    @Override
    public List<BurnedCaloriesDTO> getByActivity(ActivityRecordDTO activityRecord) {
        return null;
    }
}
