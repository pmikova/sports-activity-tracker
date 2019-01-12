package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.config.ServiceConfiguration;
import cz.muni.fi.PA165.tracker.dao.ActivityRecordDAO;
import cz.muni.fi.PA165.tracker.dao.BurnedCaloriesDAO;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.BurnedCalories;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.enums.Gender;
import cz.muni.fi.PA165.tracker.enums.UserType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * UserStatService tests.
 * @author pmikova 433345
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserStatServiceImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ActivityRecordDAO activityRecordDAO;

    @Mock
    private BurnedCaloriesDAO burnedCaloriesDAO;

    @InjectMocks
    private static final UserStatService userStatService = new UserStatServiceImpl();

    private User user;
    private User admin;
    private List<ActivityRecord> activityList;
    private List<BurnedCalories> burnedList;

    private SportActivity activity1;
    private SportActivity activity2;

    private ActivityRecord record1;
    private ActivityRecord record2;

    private BurnedCalories cals1;
    private BurnedCalories cals2;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);

        user = new User(1L);
        user.setWeight(50);
        user.setEmail("email@gmail.com");
        user.setUserType(UserType.USER);
        user.setGender(Gender.FEMALE);
        user.setName("Lucy");
        user.setSurname("Strewn");
        user.setPasswordHash("password");
        user.setBirthdate(LocalDate.of(1999, 10, 15));


        admin = new User(2L);
        admin.setWeight(80);
        admin.setEmail("rmail@gmail.com");
        admin.setUserType(UserType.ADMIN);
        admin.setGender(Gender.MALE);
        admin.setName("Othello");
        admin.setSurname("Brown");
        admin.setPasswordHash("passpasspass");
        admin.setBirthdate(LocalDate.of(1998, 12, 01));


        activity1 =  new SportActivity();
        activity1.setActivityName("running");
        activity1.setWeightCoefficient(1.3);
        activity1.setBurnedCaloriesPerHour(120);

        activity2 = new SportActivity();
        activity2.setBurnedCaloriesPerHour(300);
        activity2.setActivityName("hiding");
        activity2.setWeightCoefficient(1.1);

        record1 = new ActivityRecord();
        record1.setUser(user);
        record1.setDistance(30);
        record1.setStartTime(LocalDate.now().minusDays(1).atTime(10, 10));
        record1.setEndTime(LocalDate.now().minusDays(1).atTime(12, 12));
        record1.setSportActivity(activity1);
        record1.setAverageSpeed(12);
        record1.setDuration(Duration.between(LocalDate.now().minusDays(1).atTime(10, 10),
                LocalDate.now().minusDays(1).atTime(12, 12) ));

        record2 = new ActivityRecord();
        record2.setUser(user);
        record2.setDistance(10);
        record2.setStartTime(LocalDate.now().minusDays(3).atTime(11, 12));
        record2.setEndTime(LocalDate.now().minusDays(3).atTime(13, 14));
        record2.setSportActivity(activity2);
        record2.setAverageSpeed(8);
        record2.setDuration(Duration.between(LocalDate.now().minusDays(3).atTime(11, 12),
                LocalDate.now().minusDays(3).atTime(13, 14) ));

        cals1 = new BurnedCalories();
        cals1.setActivityRecordId(record1.getId());
        cals1.setActualWeight(87);
        cals1.setUser(user);
        cals1.setBurnedCalories(1000);

        cals2 = new BurnedCalories();
        cals2.setActivityRecordId(record2.getId());
        cals2.setActualWeight(45);
        cals2.setUser(user);
        cals2.setBurnedCalories(199);


        activityList = new ArrayList<>();
        activityList.add(record1);
        activityList.add(record2);

        burnedList = new ArrayList<>();
        burnedList.add(cals1);
        burnedList.add(cals2);

        when(activityRecordDAO.getByUser(user)).thenReturn(activityList);
        when(activityRecordDAO.getByUser(admin)).thenReturn(new ArrayList<>());
        when(burnedCaloriesDAO.getByUser(user)).thenReturn(burnedList);
        when(burnedCaloriesDAO.getByUser(admin)).thenReturn(new ArrayList<>());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getNumberOfActsNullTest() {
        userStatService.getNumberOfActivities(null);
    }

    @Test
    public void getNumberOfActsEmptyTest() {
        assertEquals(userStatService.getNumberOfActivities(admin), Integer.valueOf(0));
        verify(activityRecordDAO).getByUser(admin);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getNumberOfActsDateNullEndTest() {
        userStatService.getNumberOfActivities(user, LocalDate.now().minusDays(3), null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getNumberOfActsWrongTimeTest() {
        assertEquals( userStatService.getNumberOfActivities(user, LocalDate.now(),
                LocalDate.now().minusDays(10)), Integer.valueOf(2));
        verify(activityRecordDAO).getByUser(user);
    }

    @Test
    public void getNumberOfActsDateAnotherTest() {
        assertEquals( userStatService.getNumberOfActivities(user, LocalDate.now().minusDays(4),
                LocalDate.now()), Integer.valueOf(2));
        verify(activityRecordDAO).getByUser(user);
    }

    @Test
    public void getNumberOfActsDateTest() {
        assertEquals( userStatService.getNumberOfActivities(user, LocalDate.now().minusDays(2),
                LocalDate.now()), Integer.valueOf(1));
        verify(activityRecordDAO).getByUser(user);
    }


    @Test
    public void getNumberOfActsDateZeroTest() {
        assertEquals( userStatService.getNumberOfActivities(user, LocalDate.now().minusMonths(2),
                LocalDate.now().minusMonths(1)), Integer.valueOf(0));
        verify(activityRecordDAO).getByUser(user);
    }

    @Test
    public void getNumberOfActsTest() {
        assertEquals(userStatService.getNumberOfActivities(user), Integer.valueOf(2));
        verify(activityRecordDAO).getByUser(user);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getNumberOfActsNullUserTest() {
        userStatService.getNumberOfActivities(null, LocalDate.now().minusDays(2), LocalDate.now());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getNumberOfActsNullStartTest() {
        userStatService.getNumberOfActivities(user, null, LocalDate.now());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getAllCaloriesNullTest() {
        userStatService.getAllCalories(null);
    }

    @Test
    public void getAllCaloriesTest() {
        assertEquals(userStatService.getAllCalories(user),
                Integer.valueOf(cals1.getBurnedCalories() + cals2.getBurnedCalories()));
        verify(burnedCaloriesDAO).getByUser(user);
    }

    @Test
    public void getTotalCaloriesZeroTest() {
        assertEquals(userStatService.getAllCalories(admin), Integer.valueOf(0));
        verify(burnedCaloriesDAO).getByUser(admin);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getAllCaloriesNullUserTest() {
        userStatService.getAllCalories(null, LocalDate.now().minusDays(10), LocalDate.now());
        verify(burnedCaloriesDAO).getByUser(user);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getAllCaloriesStartNullTest() {
        userStatService.getAllCalories(user, null, LocalDate.now());
        verify(burnedCaloriesDAO).getByUser(user);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getAllCaloriesAllNullTest() {
        userStatService.getAllCalories(null, null, null);
        verify(burnedCaloriesDAO).getByUser(user);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getAllCaloriesEndNullTest() {
        userStatService.getAllCalories(user, LocalDate.now(), null);
        verify(burnedCaloriesDAO).getByUser(user);
    }
/*
    @Test
    public void getAllCaloriesBothRecordTest() {
        assertEquals(userStatService.getAllCalories(user, LocalDate.now().minusDays(20), LocalDate.now()),
                Integer.valueOf(cals1.getBurnedCalories() + cals2.getBurnedCalories()));
        verify(burnedCaloriesDAO).getByUser(user);
    }
*/
    @Test
    public void getAllCaloriesNoRecordTest() {
        assertEquals(userStatService.getAllCalories(admin,
                LocalDate.now().minusDays(40), LocalDate.now()), Integer.valueOf(0));
        verify(burnedCaloriesDAO).getByUser(admin);
    }
/*
    @Test
    public void getAllCaloriesOneEntityTest() {
        assertEquals(userStatService.getAllCalories(user, LocalDate.now().minusDays(1), LocalDate.now()),
                Integer.valueOf(cals1.getBurnedCalories()));
        verify(burnedCaloriesDAO).getByUser(user);
    }
    */
    @Test
    public void getNumberOfActsEmptyPeriodValidUserTest() {
        assertEquals(userStatService.getAllCalories(admin, LocalDate.now().minusYears(3),
                LocalDate.now().minusYears(1)), Integer.valueOf(0));
        verify(burnedCaloriesDAO).getByUser(admin);
    }
    @Test
    public void getNumberOfActsEmptyPeriodTest() {
        assertEquals(userStatService.getNumberOfActivities(admin,
                LocalDate.now().minusDays(1000), LocalDate.now().minusDays(999)), Integer.valueOf(0));
        verify(activityRecordDAO).getByUser(admin);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getNumberOfActsNullArgsTest() {
        userStatService.getNumberOfActivities(null, null, null);
        verify(activityRecordDAO).getByUser(user);
    }

    @Test
    public void countActivitiesNoRecordsTest() {
        assertEquals(userStatService.countActivitiesForUser(admin).size(), 0);
        verify(activityRecordDAO).getByUser(admin);
    }

    @Test
    public void countActivitiesValidUserTest() {
        assertEquals(userStatService.countActivitiesForUser(user).size(), 2);
        verify(activityRecordDAO).getByUser(user);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void countActivitiesNullTest() {
        userStatService.countActivitiesForUser(null);
        verify(activityRecordDAO).getByUser(user);
    }

}
