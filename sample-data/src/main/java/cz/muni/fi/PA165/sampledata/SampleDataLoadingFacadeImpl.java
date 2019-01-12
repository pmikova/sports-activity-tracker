package cz.muni.fi.PA165.sampledata;

import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.BurnedCalories;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.enums.Gender;
import cz.muni.fi.PA165.tracker.enums.UserType;
import cz.muni.fi.PA165.tracker.service.ActivityRecordService;
import cz.muni.fi.PA165.tracker.service.BurnedCaloriesService;
import cz.muni.fi.PA165.tracker.service.SportActivityService;
import cz.muni.fi.PA165.tracker.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Implementation of loading sample data.
 * @author pmikova 433345
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityRecordService activityRecordService;

    @Autowired
    private BurnedCaloriesService burnedCaloriesService;
    @Autowired
    private SportActivityService sportActivityService;


    @Override
    public void loadData(){
        SportActivity football = activity("Football", 1.2, 10);
        SportActivity running = activity("Running", 1.3, 13.5);
        SportActivity snowboarding = activity("Snowboarding", 1.1, 8);
        SportActivity weightlifting = activity("WeightLifting", 1.3, 3.5);
        SportActivity skiing = activity("Skiing", 1.2, 8);
        SportActivity powerwalking = activity("PowerWalking", 1.0, 5);
        SportActivity cycling = activity("Cycling", 1.2, 4);

        log.info("All activities loaded!");

        User user1 = user(LocalDate.of(1998, 12, 04),"passwordpassword", "Lucy",
                "Strewn", Gender.FEMALE, "email@email.com", UserType.USER, 75);
        User user2 = user(LocalDate.of(1980, 9, 10),"password", "Alex",
                "Strewn", Gender.MALE, "gmail@email.com", UserType.USER, 85);
        User admin1 = user(LocalDate.of(1970, 1, 20),"passpass", "Rudolf",
                "Rednose", Gender.MALE, "rednose@email.com", UserType.ADMIN, 79);
        User admin2 = user(LocalDate.of(1987, 3, 21),"passpass", "Alice",
                "Red", Gender.FEMALE, "alice@email.com", UserType.ADMIN, 88);
        User admin3 = user(LocalDate.of(1990, 1, 20),"passpass", "Donna",
                "Pinciotti", Gender.FEMALE, "donna@email.com", UserType.USER, 60);

        log.info("All users loaded!");

        ActivityRecord footbal1 = activityRecord(football,
                LocalDateTime.of(2018, 10, 7, 10, 12, 30),
                LocalDateTime.of(2018, 10, 7, 13, 13, 12), 0, user1);
        ActivityRecord footbal2 = activityRecord(football,
                LocalDateTime.of(2018, 12, 14, 10, 13, 30),
                LocalDateTime.of(2018, 12, 14, 13, 15, 12), 0, user2);
        ActivityRecord footbal3 = activityRecord(football,
                LocalDateTime.of(2018, 12, 14, 10, 12, 30),
                LocalDateTime.of(2018, 12, 14, 13, 18, 12), 0, user1);
        ActivityRecord footbal4 = activityRecord(football,
                LocalDateTime.of(2018, 12, 12, 10, 10, 30),
                LocalDateTime.of(2018, 12, 12, 13, 11, 12), 0, admin1);

        ActivityRecord snow1 = activityRecord(snowboarding,
                LocalDateTime.of(2018, 8, 15, 18, 11, 30),
                LocalDateTime.of(2018, 8, 15, 21, 21, 30), 11140, admin1);
        ActivityRecord snow2 = activityRecord(snowboarding,
                LocalDateTime.of(2018, 11, 13, 19, 11, 30),
                LocalDateTime.of(2018, 11, 13, 22, 21, 30), 25530, user2);
        ActivityRecord snow3 = activityRecord(snowboarding,

                LocalDateTime.of(2018, 7, 7, 11, 17, 22),
                LocalDateTime.of(2018, 7, 7, 13, 56, 11), 35518, user1);

        ActivityRecord ski1 = activityRecord(skiing,
                LocalDateTime.of(2018, 10, 11, 18, 17, 27),
                LocalDateTime.of(2018, 10, 11, 19, 56, 55), 24410, user1);

        ActivityRecord ski2 = activityRecord(skiing,
                LocalDateTime.of(2018, 2, 20, 20, 17, 44),
                LocalDateTime.of(2018, 2, 20, 23, 56, 11), 32553, user2);
        ActivityRecord ski3 = activityRecord(skiing,
                LocalDateTime.of(2018, 3, 13, 9, 17, 14),
                LocalDateTime.of(2018, 3, 13, 13, 56, 41), 25754, user1);

        ActivityRecord ski4 = activityRecord(skiing,
                LocalDateTime.of(2018, 1, 24, 12, 17, 35),
                LocalDateTime.of(2018, 1, 24, 15, 56, 25), 14845, user1);

        ActivityRecord wl1 = activityRecord(weightlifting,
                LocalDateTime.of(2018, 5, 24,18, 50, 8),
                LocalDateTime.of(2018, 5, 24, 21, 5, 6), 0, user2);
        ActivityRecord wl2 = activityRecord(weightlifting,
                LocalDateTime.of(2018, 6, 2,6, 6, 45),
                LocalDateTime.of(2018, 6, 2, 10, 33, 12), 0, user1);
        ActivityRecord wl3 = activityRecord(weightlifting,
                LocalDateTime.of(2018, 7, 3,8, 33, 25),
                LocalDateTime.of(2018, 7, 3, 10, 20, 35), 0, user2);
        ActivityRecord wl4 = activityRecord(weightlifting,
                LocalDateTime.of(2018, 12, 9,9, 17, 11),
                LocalDateTime.of(2018, 12, 9, 11, 10, 41), 0, admin1);

        log.info("All records loaded!");
        log.info("All calories loaded!");
    }


    public SportActivity activity(String name, double weight, double cph ){
        SportActivity activity = new SportActivity();
        activity.setActivityName(name);
        activity.setWeightCoefficient(weight);
        activity.setBurnedCaloriesPerHour(cph);
        sportActivityService.create(activity);
        return activity;
    }

    public User user(LocalDate bdate, String pwhash, String name, String surname, Gender gender, String email,
                      UserType type, long weight){
        User user = new User();
        user.setBirthdate(bdate);
        user.setPasswordHash(pwhash);
        user.setSurname(surname);
        user.setName(name);
        user.setGender(gender);
        user.setEmail(email);
        user.setUserType(type);
        user.setWeight(weight);
        userService.register(user, user.getPasswordHash());
        return user;
    }

    public BurnedCalories calories(int cals, User user, long weight, ActivityRecord activityRecord){
        BurnedCalories calories = new BurnedCalories();
        calories.setBurnedCalories(cals);
        calories.setUser(user);
        calories.setActualWeight(weight);
        calories.setActivityRecordId(activityRecord.getId());
        burnedCaloriesService.create(calories);
        return calories;
    }

    public ActivityRecord activityRecord(SportActivity activity, LocalDateTime startTime, LocalDateTime endTime,
                                          int distance, User user){
        ActivityRecord record = new ActivityRecord();
        record.setSportActivity(activity);
        record.setStartTime(startTime);
        record.setEndTime(endTime);
        record.setDistance(distance);
        record.setUser(user);
        activityRecordService.create(record);
        return record;
    }

}
