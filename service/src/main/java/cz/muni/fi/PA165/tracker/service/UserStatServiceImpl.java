package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.dao.ActivityRecordDAO;
import cz.muni.fi.PA165.tracker.dao.BurnedCaloriesDAO;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of UserStatService.
 * @author pmikova 433345
 */

@Service
public class UserStatServiceImpl implements UserStatService {

    @Inject
    private ActivityRecordDAO ard;

    @Inject
    private BurnedCaloriesDAO bcd;

    @Override
    public Integer getAllCalories(User user) {
        if (user == null) throw new IllegalArgumentException("User is null");

        return bcd.getByUser(user).stream()
                .filter(r -> r.getBurnedCalories() > 0)
                .mapToInt(r -> r.getBurnedCalories())
                .sum();
    }


    @Override
    public Integer getAllCalories(User user, LocalDate start, LocalDate end) {
        if (user == null) {
            throw new IllegalArgumentException("User can not be null!");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start date can not be null!");
        }
        if (end == null) {
            throw new IllegalArgumentException("End date can not be null!");
        }
        if (start.isAfter(end)){
            throw new IllegalArgumentException("End time must be before start time!");
        }

        return bcd.getByUser(user).stream()
                .filter(r -> r.getBurnedCalories() > 0 && r.getActivityRecord().getStartTime() != null)
                .filter(r -> !r.getActivityRecord().getStartTime().toLocalDate().isBefore(start))
                .filter(r -> !r.getActivityRecord().getStartTime().toLocalDate().isAfter(end))
                .mapToInt(r -> r.getBurnedCalories())
                .sum();
    }

    @Override
    public Integer getNumberOfActivities(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }

        return ard.getByUser(user).size();
    }

    @Override
    public Integer getNumberOfActivities(User user, LocalDate start, LocalDate end) {
        if (user == null){
            throw new IllegalArgumentException("User can not be null!");
        }
        if (start == null){
            throw new IllegalArgumentException("From date can not be null!");
        }
        if (end == null){
            throw new IllegalArgumentException("End date can not be null!");
        }
        if (start.isAfter(end)){
            throw new IllegalArgumentException("End time must be before start time!");
        }
        return (int) ard.getByUser(user).stream()
                .filter(r -> r.getStartTime() != null)
                .filter(r -> !r.getStartTime().toLocalDate().isAfter(end))
                .filter(r -> !r.getEndTime().toLocalDate().isBefore(start))
                .count();
    }

    @Override
    public Map<SportActivity, Integer> countActivitiesForUser(User user) {
        if (user == null) throw new IllegalArgumentException("User can not be null!");

        return ard.getByUser(user)
                .stream()
                .collect(
                        Collectors.groupingBy(ActivityRecord::getSportActivity,
                                Collectors.reducing(0, e -> 1, Integer::sum))
                );
    }
}
