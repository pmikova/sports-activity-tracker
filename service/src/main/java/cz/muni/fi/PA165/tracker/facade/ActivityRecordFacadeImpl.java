package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.dto.ActivityRecordCreateDTO;
import cz.muni.fi.PA165.tracker.dto.ActivityRecordDTO;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import cz.muni.fi.PA165.tracker.service.ActivityRecordService;
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


    @Override
    public void create(ActivityRecordCreateDTO activityRecordDTO) {
        ActivityRecord activityRecord = mappingService.mapTo(activityRecordDTO, ActivityRecord.class);
        activityRecordService.create(activityRecord);
    }

    @Override
    public void update(ActivityRecordDTO activityRecordDTO) {
        ActivityRecord activityRecord = mappingService.mapTo(activityRecordDTO, ActivityRecord.class);
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

}
