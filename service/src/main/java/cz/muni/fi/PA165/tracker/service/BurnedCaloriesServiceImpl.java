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

    @Override
    public void create(BurnedCalories burnedCalories) {
        computeBurnedCalories(burnedCalories);
        burnedCaloriesDAO.create(burnedCalories);
    }

    @Override
    public void update(BurnedCalories burnedCalories) {
        computeBurnedCalories(burnedCalories);
        burnedCaloriesDAO.update(burnedCalories);
    }

    @Override
    public void delete(BurnedCalories burnedCalories) {
        burnedCaloriesDAO.delete(burnedCalories);
    }

    @Override
    public BurnedCalories getById(Long id) {
        return burnedCaloriesDAO.getById(id);
    }

    @Override
    public List<BurnedCalories> getAll() {
        return burnedCaloriesDAO.getAll();
    }

    @Override
    public List<BurnedCalories> getByUser(User user) {
        return burnedCaloriesDAO.getByUser(user);
    }

    @Override
    public List<BurnedCalories> getByActivity(ActivityRecord activityRecord) {
        return burnedCaloriesDAO.getByActivity(activityRecord);
    }

    private void computeBurnedCalories(BurnedCalories burnedCalories) {
        User user = burnedCalories.getUser();
        Duration duration = burnedCalories.getActivityRecord().getDuration();
        SportActivity sportActivity = burnedCalories.getActivityRecord().getSportActivity();
        double bmr;
        int age = Period.between(user.getBirthdate(), LocalDate.now()).getYears();
        if (user.getGender() == Gender.MALE)
            bmr = 968 + (6.23 * (burnedCalories.getActualWeight() / 2.2)) - (6.8 * age);
        else
            bmr = 1506 + (4.35 * (burnedCalories.getActualWeight() / 2.2)) - (4.7 * age);

        int burnedCaloriesAmount = (int) Math.round(bmr * sportActivity.getBurnedCaloriesPerHour() * duration.toHours());
        burnedCalories.setBurnedCalories(burnedCaloriesAmount);
    }
}
