package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.dto.ActivityRecordCreateDTO;
import cz.muni.fi.PA165.tracker.dto.ActivityRecordDTO;
import cz.muni.fi.PA165.tracker.dto.ActivityRecordUpdateDTO;
import cz.muni.fi.PA165.tracker.dto.UserDTO;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.exceptions.NotExistingEntityException;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import cz.muni.fi.PA165.tracker.service.ActivityRecordService;
import cz.muni.fi.PA165.tracker.service.SportActivityService;
import cz.muni.fi.PA165.tracker.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Activity Record Facade implementation.
 * @author Dominik-Bujna
 */


@Service
@Transactional

public class ActivityRecordFacadeImpl implements  ActivityRecordFacade{

    @Inject
    private MappingService mappingService;

    @Inject
    private ActivityRecordService activityRecordService;

    @Inject
    private UserService userService;

    @Inject
    private SportActivityService activityService;


    @Override
    public void create(ActivityRecordCreateDTO activityRecordCreateDTO) {


        if (activityRecordCreateDTO == null) {
            throw new IllegalArgumentException("activity report cannot be null");
        }
        ActivityRecord activityRecord = mappingService.mapTo(activityRecordCreateDTO, ActivityRecord.class);

        activityRecord.setUser(getUserById(activityRecordCreateDTO.getUserId()));
        activityRecord.setSportActivity(getActivityById(activityRecordCreateDTO.getSportActivityId()));

        activityRecordService.create(activityRecord);
    }

    private User getUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id is null");
        }
        User user = userService.getById(id);
        if (user == null) {
            throw new NotExistingEntityException("User does not exist. Id: " + id);
        }
        return user;
    }

    private SportActivity getActivityById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Activity id is null");
        }
        SportActivity sportActivity = activityService.getById(id);
        if (sportActivity == null) {
            throw new NotExistingEntityException("Activity does not exist. Id: " + id);
        }
        return sportActivity;
    }

    @Override
    public void update(ActivityRecordUpdateDTO activityRecordDTO) {
        if (activityRecordDTO == null) {
            throw new IllegalArgumentException("activity report cannot be null");
        }
        ActivityRecord activityRecord = mappingService.mapTo(activityRecordDTO, ActivityRecord.class);

        if (activityRecordService.getById(activityRecord.getId()) == null) {
            throw new NotExistingEntityException("Can not update non existing activity report");
        }

        activityRecord.setUser(getUserById(activityRecordDTO.getUserId()));
        activityRecord.setSportActivity(getActivityById(activityRecordDTO.getSportActivityId()));

        activityRecordService.update(activityRecord);
    }

    @Override
    public void delete(ActivityRecordDTO activityRecordDTO) {
        ActivityRecord activityRecord = mappingService.mapTo(activityRecordDTO, ActivityRecord.class);
        activityRecordService.delete(activityRecord);
    }

    @Override
    public ActivityRecordDTO getById(Long id) {
        return mappingService.mapTo(activityRecordService.getById(id), ActivityRecordDTO.class);

    }

    @Override
    public List<ActivityRecordDTO> getAll() {
        return mappingService.mapTo(activityRecordService.getAll(), ActivityRecordDTO.class);
    }
    @Override
    public List<ActivityRecordDTO> getByUser(UserDTO userDto){
        User user = mappingService.mapTo(userDto, User.class);
        return mappingService.mapTo(activityRecordService.getByUser(user), ActivityRecordDTO.class);

    }

}
