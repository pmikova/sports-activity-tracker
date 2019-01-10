package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.dao.BurnedCaloriesDAO;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.BurnedCalories;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.enums.Gender;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Implementation of BurnedCaloriesService
 * @author HonzaOstrava
 */
@Service
public class BurnedCaloriesServiceImpl implements BurnedCaloriesService {

    @Inject
    private BurnedCaloriesDAO burnedCaloriesDAO;

    @Inject
    private UserService userService;

    @Inject
    private ActivityRecordService activityRecordService;

    @Override
    public void create(BurnedCalories burnedCalories) {
        if (burnedCalories == null){
            throw new IllegalArgumentException("Burned calories can not be null!");
        }
        burnedCaloriesDAO.create(burnedCalories);
    }

    @Override
    public void update(BurnedCalories burnedCalories) {
        if (burnedCalories == null){
            throw new IllegalArgumentException("Burned calories can not be null!");
        }
        burnedCaloriesDAO.update(burnedCalories);
    }

    @Override
    public void delete(BurnedCalories burnedCalories) {
        if (burnedCalories == null){
            throw new IllegalArgumentException("Burned calories can not be null!");
        }
        burnedCaloriesDAO.delete(burnedCalories);
    }

    @Override
    public BurnedCalories getById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("ID calories can not be null!");
        }
        return burnedCaloriesDAO.getById(id);
    }

    @Override
    public List<BurnedCalories> getAll() {
        return burnedCaloriesDAO.getAll();
    }

    @Override
    public List<BurnedCalories> getByUser(User user) {
        if (user == null){
            throw new IllegalArgumentException("User can not be null!");
        }
        return burnedCaloriesDAO.getByUser(user);
    }

    @Override
    public BurnedCalories getByActivityId(Long activityRecord) {
        if (activityRecord == null){
            throw new IllegalArgumentException("Activity record can not be null!");
        }
        return burnedCaloriesDAO.getByActivityRecordId(activityRecord);
    }

    @Override
    public void computeBurnedCalories(BurnedCalories burnedCalories) {
        if (burnedCalories == null){
            throw new IllegalArgumentException("Burned calories can not be null!");
        }
        User user = burnedCalories.getUser();
        ActivityRecord activityRecord = activityRecordService.getById(burnedCalories.getActivityRecordId());
        if (user == null || activityRecord == null) {
            throw new IllegalArgumentException("Burned calories must be complete!");
        }
        Duration duration = activityRecord.getDuration();
        SportActivity sportActivity = activityRecord.getSportActivity();
        if (duration == null || sportActivity == null) {
            throw new IllegalArgumentException("Burned calories must be complete!");
        }
        double bmr;
        int age = userService.getAge(user);
        if (user.getGender() == Gender.MALE)
            bmr = (916 + (13.7 * burnedCalories.getActualWeight()) - (6.8 * age)) * 4;
        else
            bmr = (943 + (9.6 * burnedCalories.getActualWeight()) - (4.7 * age)) * 4;

        int burnedCaloriesAmount = (int) Math.round(bmr * sportActivity.getBurnedCaloriesPerHour() * duration.toHours());
        burnedCalories.setBurnedCalories(burnedCaloriesAmount);
    }
}
