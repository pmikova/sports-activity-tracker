package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.dao.ActivityRecordDAO;
import cz.muni.fi.PA165.tracker.dao.BurnedCaloriesDAO;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.BurnedCalories;
import cz.muni.fi.PA165.tracker.entities.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implements services to work with an activity record.
 * @author Dominik-Bujna
 */

@Service
public class ActivityRecordServiceImpl implements ActivityRecordService {

    @Inject
    private ActivityRecordDAO activityRecordDAO;

    @Inject
    private BurnedCaloriesDAO bcd;


    @Override
    public Duration calculateDuration(ActivityRecord activityRecord) {
        if(activityRecord == null) throw new IllegalArgumentException("ActivityRecord cannot be null.");
        LocalDateTime startTime = activityRecord.getStartTime();
        LocalDateTime endTime = activityRecord.getEndTime();
        if (startTime == null || endTime == null){
            throw new IllegalArgumentException("startTime and endTime can not be null!");
        }else{
            return Duration.between(startTime, endTime);
        }
    }

    @Override
    public double calculateAverageSpeed(ActivityRecord activityRecord) {
        if(activityRecord == null) throw new IllegalArgumentException("ActivityRecord cannot be null.");
        double distance = (double) activityRecord.getDistance()/1000.0;
        if (distance !=0 ){
            Duration duration = activityRecord.getDuration();
            if (duration == null){
                throw new IllegalArgumentException("Duration needs to be calculated");
            }else{
                return distance/(duration.toHours());
            }
        }
        return 0.0;
    }

    @Override
    public void create(ActivityRecord activityRecord) {
        if(activityRecord == null) throw new IllegalArgumentException("ActivityRecord cannot be null.");
        activityRecord.setDuration(calculateDuration(activityRecord));
        activityRecord.setAverageSpeed(calculateAverageSpeed(activityRecord));
        activityRecordDAO.create(activityRecord);
        User user = activityRecord.getUser();
        if(user == null){
            throw new IllegalArgumentException("User cannot be null.");
        }
        user.addActivityRecord(activityRecord);
    }

    @Override
    public void update(ActivityRecord activityRecord) {
        if(activityRecord == null) throw new IllegalArgumentException("ActivityRecord cannot be null.");
        activityRecord.setDuration(calculateDuration(activityRecord));
        activityRecord.setAverageSpeed(calculateAverageSpeed(activityRecord));
        activityRecordDAO.update(activityRecord);
    }

    @Override
    public void delete(ActivityRecord activityRecord) {
        if(activityRecord == null) throw new IllegalArgumentException("ActivityRecord cannot be null.");
        // assure that associated burned calories are destroyed too
        BurnedCalories bc  = bcd.getByActivityRecordId(activityRecord.getId());
        bcd.delete(bc);
        activityRecordDAO.delete(activityRecord);
    }

    @Override
    public ActivityRecord getById(Long id) {
        if(id == null) throw new IllegalArgumentException("id cannot be null.");
        return activityRecordDAO.getById(id);
    }

    @Override
    public List<ActivityRecord> getAll() {
        return activityRecordDAO.getAll();
    }

    @Override
    public List<ActivityRecord> getByUser(User user) {
        return activityRecordDAO.getByUser(user);
    }
}
