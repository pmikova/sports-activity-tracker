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
        SportActivity football = activity("Football", 1.2, 200);
        SportActivity running = activity("Running", 1.3, 400);
        SportActivity snowboarding = activity("Snowboarding", 1.1, 150);
        SportActivity weightlifting = activity("WeightLifting", 1.3, 450);
        SportActivity skiing = activity("Skiing", 1.2, 150);
        SportActivity powerwalking = activity("PowerWalking", 1.0, 100);
        SportActivity cycling = activity("Cycling", 1.2, 190);

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
                LocalDateTime.of(2018, 11, 14, 10, 13, 30),
                LocalDateTime.of(2018, 11, 14, 13, 15, 12), 0, user2);
        ActivityRecord footbal3 = activityRecord(football,
                LocalDateTime.of(2018, 3, 14, 10, 12, 30),
                LocalDateTime.of(2018, 3, 14, 13, 18, 12), 0, user1);
        ActivityRecord footbal4 = activityRecord(football,
                LocalDateTime.of(2018, 5, 10, 10, 10, 30),
                LocalDateTime.of(2018, 5, 10, 13, 11, 12), 0, admin1);

        ActivityRecord snow1 = activityRecord(snowboarding,
                LocalDateTime.of(2018, 8, 15, 18, 11, 30),
                LocalDateTime.of(2018, 8, 15, 21, 21, 30), 40, admin1);
        ActivityRecord snow2 = activityRecord(snowboarding,
                LocalDateTime.of(2018, 11, 13, 19, 11, 30),
                LocalDateTime.of(2018, 11, 13, 22, 21, 30), 30, user2);
        ActivityRecord snow3 = activityRecord(snowboarding,
                LocalDateTime.of(2018, 7, 7, 11, 17, 22),
                LocalDateTime.of(2018, 7, 7, 13, 56, 11), 18, user1);

        ActivityRecord ski1 = activityRecord(skiing,
                LocalDateTime.of(2018, 10, 11, 18, 17, 27),
                LocalDateTime.of(2018, 10, 11, 19, 56, 55), 20, user1);
        ActivityRecord ski2 = activityRecord(skiing,
                LocalDateTime.of(2018, 2, 20, 20, 17, 44),
                LocalDateTime.of(2018, 2, 20, 23, 56, 11), 33, user2);
        ActivityRecord ski3 = activityRecord(skiing,
                LocalDateTime.of(2018, 3, 13, 9, 17, 14),
                LocalDateTime.of(2018, 3, 13, 13, 56, 41), 40, user1);
        ActivityRecord ski4 = activityRecord(skiing,
                LocalDateTime.of(2018, 1, 24, 12, 17, 35),
                LocalDateTime.of(2018, 1, 24, 15, 56, 25), 45, user1);

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
                LocalDateTime.of(2018, 9, 9,9, 17, 11),
                LocalDateTime.of(2018, 9, 9, 11, 10, 41), 0, admin1);

        log.info("All records loaded!");

        BurnedCalories calsF1 = calories(450, footbal1.getUser(), 75, footbal1);
        BurnedCalories calsF2 = calories(300, footbal2.getUser(), 80, footbal2);
        BurnedCalories calsF3 = calories(660, footbal3.getUser(), 95, footbal3);
        BurnedCalories calsF4 = calories(598, footbal4.getUser(), 75, footbal4);

        BurnedCalories calsSK1 = calories(600, ski1.getUser(), 75, ski1);
        BurnedCalories calsSK2 = calories(450, ski2.getUser(), 58, ski2);
        BurnedCalories calsSK3 = calories(750, ski3.getUser(), 78, ski3);
        BurnedCalories calsSK4 = calories(280, ski4.getUser(), 77, ski4);

        BurnedCalories calsSN1 = calories(600, snow1.getUser(), 76, snow1);
        BurnedCalories calsSN2 = calories(750, snow2.getUser(), 78, snow2);
        BurnedCalories calsSN3 = calories(700, snow3.getUser(), 80, snow3);

        BurnedCalories calsWL1 = calories(289, wl1.getUser(), 80, wl1);
        BurnedCalories calsWL2 = calories(289, wl2.getUser(), 80, wl2);
        BurnedCalories calsWL3 = calories(289, wl3.getUser(), 80, wl3);
        BurnedCalories calsWL4 = calories(289, wl4.getUser(), 80, wl4);

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
        calories.setActivityRecord(activityRecord);
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
