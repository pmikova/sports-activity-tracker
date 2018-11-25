package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.dao.ActivityRecordDAO;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.User;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implements services to work with an activity record.
 * @author Dominik-Bujna
 */

public class ActivityRecordServiceImpl implements ActivityRecordService {

    @Inject
    private ActivityRecordDAO activityRecordDAO;


    @Override
    public Duration calculateDuration(ActivityRecord activityRecord) {
        LocalDateTime startTime = activityRecord.getStartTime();
        LocalDateTime endTime = activityRecord.getEndTime();
        return Duration.between(startTime, endTime);
    }

    @Override
    public double calculateAverageSpeed(ActivityRecord activityRecord) {
        double distance = (double)activityRecord.getDistance();
        if (distance !=0){
            Duration duration = activityRecord.getDuration();
            return distance/(duration.toHours());
        }
        return 0.0;
    }

    @Override
    public void create(ActivityRecord activityRecord) {
        activityRecord.setDuration(calculateDuration(activityRecord));
        activityRecord.setAverageSpeed(calculateAverageSpeed(activityRecord));
        activityRecordDAO.create(activityRecord);
        User user = activityRecord.getUser();
        user.addActivityRecord(activityRecord);
    }

    @Override
    public void update(ActivityRecord activityRecord) {
        activityRecordDAO.update(activityRecord);
    }

    @Override
    public void delete(ActivityRecord activityRecord) {
        activityRecordDAO.delete(activityRecord);
    }

    @Override
    public ActivityRecord getById(Long id) {
        return activityRecordDAO.getById(id);
    }

    @Override
    public List<ActivityRecord> getAll() {
        return activityRecordDAO.getAll();
    }
}
