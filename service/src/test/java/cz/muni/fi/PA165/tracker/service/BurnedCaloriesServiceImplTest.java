package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.config.ServiceConfiguration;
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
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * BurnedCalories service tests.
 * @author HonzaOstrava
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BurnedCaloriesServiceImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private BurnedCaloriesDAO burnedCaloriesDAO;

    @Mock
    private UserService userService = new UserServiceImpl();

    @InjectMocks
    private BurnedCaloriesService burnedCaloriesService = new BurnedCaloriesServiceImpl();

    private BurnedCalories burnedCalories1;
    private BurnedCalories burnedCalories2;
    private BurnedCalories burnedCalories3;
    private BurnedCalories burnedCalories4;
    private BurnedCalories burnedCalories5;
    private BurnedCalories burnedCalories6;

    private User user;

    private ActivityRecord activityRecord1;
    private ActivityRecord activityRecord2;
    private ActivityRecord activityRecord3;

    private SportActivity sportActivity;

    @BeforeClass
    public void setupMockito() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        user = new User();
        user.setWeight(50);
        user.setEmail("email@gmail.com");
        user.setUserType(UserType.USER);
        user.setGender(Gender.FEMALE);
        user.setName("Lucy");
        user.setSurname("Strewn");
        user.setPasswordHash("password");
        user.setBirthdate(LocalDate.of(1999, 10, 15));

        sportActivity = new SportActivity();
        sportActivity.setWeightCoefficient(1);
        sportActivity.setBurnedCaloriesPerHour(100);
        sportActivity.setActivityName("Running");

        activityRecord1 = new ActivityRecord();
        activityRecord1.setSportActivity(sportActivity);
        activityRecord1.setUser(user);
        activityRecord1.setDuration(Duration.of(1, HOURS));
        activityRecord1.setStartTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 10, 0, 0));
        activityRecord1.setEndTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 11, 0, 0));
        activityRecord1.setDistance(15900);

        activityRecord2 = new ActivityRecord();
        activityRecord2.setSportActivity(null);
        activityRecord2.setUser(user);
        activityRecord2.setDuration(Duration.of(1, HOURS));
        activityRecord2.setStartTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 10, 0, 0));
        activityRecord2.setEndTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 11, 0, 0));
        activityRecord2.setDistance(15900);

        activityRecord3 = new ActivityRecord();
        activityRecord3.setSportActivity(sportActivity);
        activityRecord3.setUser(user);
        activityRecord3.setDuration(null);
        activityRecord3.setStartTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 10, 0, 0));
        activityRecord3.setEndTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 11, 0, 0));
        activityRecord3.setDistance(15900);

        burnedCalories1 = new BurnedCalories();
        burnedCalories1.setActivityRecord(activityRecord1);
        burnedCalories1.setUser(user);
        burnedCalories1.setActualWeight(user.getWeight());

        burnedCalories2 = new BurnedCalories();
        burnedCalories2.setActivityRecord(new ActivityRecord());
        burnedCalories2.setUser(new User());
        burnedCalories2.setActualWeight(60);

        burnedCalories3 = new BurnedCalories();
        burnedCalories3.setActivityRecord(activityRecord1);
        burnedCalories3.setUser(null);
        burnedCalories3.setActualWeight(0);

        burnedCalories4 = new BurnedCalories();
        burnedCalories4.setActivityRecord(null);
        burnedCalories4.setUser(user);
        burnedCalories4.setActualWeight(user.getWeight());

        burnedCalories5 = new BurnedCalories();
        burnedCalories5.setActivityRecord(activityRecord2);
        burnedCalories5.setUser(user);
        burnedCalories5.setActualWeight(user.getWeight());

        burnedCalories6 = new BurnedCalories();
        burnedCalories6.setActivityRecord(activityRecord3);
        burnedCalories6.setUser(user);
        burnedCalories6.setActualWeight(user.getWeight());
    }

    @Test
    public void testCreate() {
        burnedCaloriesService.create(burnedCalories2);
        verify(burnedCaloriesDAO).create(burnedCalories2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNull() {
        burnedCaloriesService.create(null);
    }

    @Test
    public void testUpdate() {
        burnedCalories2.setActualWeight(70);
        burnedCaloriesService.update(burnedCalories2);
        verify(burnedCaloriesDAO).update(burnedCalories2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNull() {
        burnedCaloriesService.update(null);
    }

    @Test
    public void testDelete() {
        burnedCaloriesService.delete(burnedCalories2);
        verify(burnedCaloriesDAO).delete(burnedCalories2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNull() {
        burnedCaloriesService.delete(null);
    }

    @Test
    public void testGetById() {
        when(burnedCaloriesDAO.getById(1L)).thenReturn(burnedCalories2);
        BurnedCalories burnedCalories = burnedCaloriesService.getById(1L);
        verify(burnedCaloriesDAO).getById(1L);
        Assert.assertEquals(burnedCalories, burnedCalories2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetByIdNull() {
        burnedCaloriesService.getById(null);
    }

    @Test
    public void testGetByIdNonexistent() {
        when(burnedCaloriesDAO.getById(10L)).thenReturn(null);
        BurnedCalories burnedCalories = burnedCaloriesService.getById(10L);
        verify(burnedCaloriesDAO).getById(10L);
        Assert.assertNull(burnedCalories);
    }

    @Test
    public void testGetAll() {
        when(burnedCaloriesDAO.getAll()).thenReturn(Arrays.asList(burnedCalories1, burnedCalories2));
        List<BurnedCalories> burnedCalories = burnedCaloriesService.getAll();
        verify(burnedCaloriesDAO).getAll();
        Assert.assertEquals(2, burnedCalories.size());
        Assert.assertTrue(burnedCalories.contains(burnedCalories1));
        Assert.assertTrue(burnedCalories.contains(burnedCalories2));
    }

    @Test
    public void testGetByUser() {
        when(burnedCaloriesDAO.getByUser(user)).thenReturn(Arrays.asList(burnedCalories1));
        List<BurnedCalories> burnedCalories = burnedCaloriesService.getByUser(user);
        verify(burnedCaloriesDAO).getByUser(user);
        Assert.assertEquals(1, burnedCalories.size());
        Assert.assertTrue(burnedCalories.contains(burnedCalories1));
    }

    @Test
    public void testGetByUserNonexistent() {
        User notExisting = new User();
        when(burnedCaloriesDAO.getByUser(notExisting)).thenReturn(Collections.emptyList());
        Assert.assertTrue(burnedCaloriesService.getByUser(notExisting).isEmpty());
        verify(burnedCaloriesDAO).getByUser(notExisting);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetByUserNull() {
        burnedCaloriesService.getByUser(null);
    }

    @Test
    public void testGetByActivity() {
        when(burnedCaloriesService.getByActivity(activityRecord1)).thenReturn(Arrays.asList(burnedCalories1));
        List<BurnedCalories> burnedCalories = burnedCaloriesService.getByActivity(activityRecord1);
        verify(burnedCaloriesDAO).getByActivity(activityRecord1);
        Assert.assertEquals(1, burnedCalories.size());
        Assert.assertTrue(burnedCalories.contains(burnedCalories1));
    }

    @Test
    public void testGetByActivityNonexistent() {
        ActivityRecord notExisting = new ActivityRecord();
        when(burnedCaloriesService.getByActivity(notExisting)).thenReturn(Collections.emptyList());
        Assert.assertTrue(burnedCaloriesService.getByActivity(notExisting).isEmpty());
        verify(burnedCaloriesDAO).getByActivity(notExisting);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetByActivityNull() {
        burnedCaloriesService.getByActivity(null);
    }

    @Test
    public void testComputeBurnedCalories() {
        when(userService.getAge(user)).thenReturn(19);
        burnedCaloriesService.computeBurnedCalories(burnedCalories1);
        Assert.assertEquals(533480, burnedCalories1.getBurnedCalories());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testComputeBurnedCaloriesNull() {
        burnedCaloriesService.computeBurnedCalories(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testComputeBurnedCaloriesNullUser() {
        burnedCaloriesService.computeBurnedCalories(burnedCalories3);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testComputeBurnedCaloriesNullActivityRecord() {
        burnedCaloriesService.computeBurnedCalories(burnedCalories4);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testComputeBurnedCaloriesNullSportActivity() {
        burnedCaloriesService.computeBurnedCalories(burnedCalories5);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testComputeBurnedCaloriesNullDuration() {
        burnedCaloriesService.computeBurnedCalories(burnedCalories6);
    }
}
