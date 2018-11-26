package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.dto.ActivityRecordDTO;
import cz.muni.fi.PA165.tracker.dto.BurnedCaloriesCreateDTO;
import cz.muni.fi.PA165.tracker.dto.BurnedCaloriesDTO;
import cz.muni.fi.PA165.tracker.dto.UserDTO;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.BurnedCalories;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import cz.muni.fi.PA165.tracker.service.BurnedCaloriesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Burned Calories Facade implementation.
 * @author HonzaOstrava
 */
@Service
@Transactional
public class BurnedCaloriesFacadeImpl implements BurnedCaloriesFacade {

    @Inject
    private MappingService mappingService;

    @Inject
    private BurnedCaloriesService burnedCaloriesService;

    @Override
    public void create(BurnedCaloriesCreateDTO burnedCaloriesDTO) {
        BurnedCalories burnedCalories = mappingService.mapTo(burnedCaloriesDTO, BurnedCalories.class);
        burnedCaloriesService.computeBurnedCalories(burnedCalories);
        burnedCaloriesService.create(burnedCalories);
    }

    @Override
    public void delete(BurnedCaloriesDTO burnedCaloriesDTO) {
        BurnedCalories burnedCalories = mappingService.mapTo(burnedCaloriesDTO, BurnedCalories.class);
        burnedCaloriesService.delete(burnedCalories);
    }

    @Override
    public void update(BurnedCaloriesDTO burnedCaloriesDTO) {
        BurnedCalories burnedCalories = mappingService.mapTo(burnedCaloriesDTO, BurnedCalories.class);
        burnedCaloriesService.computeBurnedCalories(burnedCalories);
        burnedCaloriesService.update(burnedCalories);
    }

    @Override
    public List<BurnedCaloriesDTO> getAll() {
        return mappingService.mapTo(burnedCaloriesService.getAll(), BurnedCaloriesDTO.class);
    }

    @Override
    public BurnedCaloriesDTO getById(Long id) {
        return mappingService.mapTo(burnedCaloriesService.getById(id), BurnedCaloriesDTO.class);
    }

    @Override
    public List<BurnedCaloriesDTO> getByUser(UserDTO userDTO) {
        User user = mappingService.mapTo(userDTO, User.class);
        return mappingService.mapTo(burnedCaloriesService.getByUser(user), BurnedCaloriesDTO.class);
    }

    @Override
    public List<BurnedCaloriesDTO> getByActivity(ActivityRecordDTO activityRecordDTO) {
        ActivityRecord activityRecord = mappingService.mapTo(activityRecordDTO, ActivityRecord.class);
        return mappingService.mapTo(burnedCaloriesService.getByActivity(activityRecord), BurnedCaloriesDTO.class);
    }
}
