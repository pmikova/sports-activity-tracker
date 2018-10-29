package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.PersistenceApplicationContext;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.BurnedCalories;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.enums.Gender;
import cz.muni.fi.PA165.tracker.enums.UserType;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Tests for BurnedCaloriesDAOImpl class.
 * @author HonzaOstrava
 *
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BurnedCaloriesDAOImplTest extends AbstractTestNGSpringContextTests {

    @Inject
    BurnedCaloriesDAO burnedCaloriesDAO;
    @Inject
    UserDAO userDAO;
    @Inject
    ActivityRecordDAO activityRecordDAO;
    @Inject
    SportActivityDAO sportActivityDAO;

    @PersistenceContext
    private EntityManager entityManager;

    private BurnedCalories burnedCalories1;
    private BurnedCalories burnedCalories2;
    private BurnedCalories burnedCalories3;
    private BurnedCalories burnedCalories4;

    private User prepareUser(String email, String name, String surname, Gender gender, String password, int weight, LocalDate birthdate, UserType userType) {
        User user = new User();
        user.setEmail(email);
        user.setGender(gender);
        user.setName(name);
        user.setSurname(surname);
        user.setWeight(weight);
        user.setPasswordHash(password);
        user.setBirthdate(birthdate);
        user.setUserType(userType);
        return user;
    }

    private SportActivity prepareSportActivity(String activityName, double burnedCaloriesPerHour, double weightCoefficient) {
        SportActivity sportActivity = new SportActivity();
        sportActivity.setActivityName(activityName);
        sportActivity.setBurnedCaloriesPerHour(burnedCaloriesPerHour);
        sportActivity.setWeightCoefficient(weightCoefficient);
        return sportActivity;
    }

    private ActivityRecord prepareActivityRecord(LocalDateTime startTime, LocalDateTime endTime, double averageSpeed, int distance, User user, SportActivity sportActivity, int burnedCalories) {
        ActivityRecord activityRecord = new ActivityRecord();
        activityRecord.setStartTime(startTime);
        activityRecord.setEndTime(endTime);
        activityRecord.setDuration(Duration.between(startTime, endTime));
        activityRecord.setAverageSpeed(averageSpeed);
        activityRecord.setDistance(distance);
        activityRecord.setUser(user);
        activityRecord.setSportActivity(sportActivity);
        activityRecord.setBurnedCalories(burnedCalories);
        return activityRecord;
    }

    private BurnedCalories prepareBurnedCalories(ActivityRecord activityRecord, User user, double actualWeight, int burnedCaloriesAmount) {
        BurnedCalories burnedCalories = new BurnedCalories();
        burnedCalories.setActivityRecord(activityRecord);
        burnedCalories.setUser(user);
        burnedCalories.setActualWeight(actualWeight);
        burnedCalories.setBurnedCalories(burnedCaloriesAmount);
        return burnedCalories;
    }

    @BeforeMethod
    public void setUp() {
        LocalDate birthdate1 = LocalDate.of(1950,10,13);
        User user1 = prepareUser("johnd@email.com", "John", "Doe", Gender.MALE, "password", 80, birthdate1, UserType.USER);
        userDAO.create(user1);

        LocalDate birthdate2 = LocalDate.of(1991, 8, 16);
        User user2 = prepareUser("lucy@email.com", "Lucy", "Doe", Gender.FEMALE, "password2", 55, birthdate2, UserType.USER);
        userDAO.create(user2);

        SportActivity sportActivity1 = prepareSportActivity("Running", 1000, 1.0);
        sportActivityDAO.create(sportActivity1);

        SportActivity sportActivity2 = prepareSportActivity("Swimming", 1500, 0.8);
        sportActivityDAO.create(sportActivity2);

        LocalDateTime startTime1 = LocalDateTime.of(2018, 10, 25, 15, 0);
        LocalDateTime endTime1 = LocalDateTime.of(2018, 10, 25, 16, 0);
        ActivityRecord activityRecord1 = prepareActivityRecord(startTime1, endTime1, 15, 15, user1, sportActivity1, 1000);
        activityRecordDAO.create(activityRecord1);

        LocalDateTime startTime2 = LocalDateTime.of(2018, 10, 26, 15, 0);
        LocalDateTime endTime2 = LocalDateTime.of(2018, 10, 26, 16, 0);
        ActivityRecord activityRecord2 = prepareActivityRecord(startTime2, endTime2, 15, 15, user1, sportActivity2, 1500);
        activityRecordDAO.create(activityRecord2);

        LocalDateTime startTime3 = LocalDateTime.of(2018, 10, 27, 15, 0);
        LocalDateTime endTime3 = LocalDateTime.of(2018, 10, 27, 16, 0);
        ActivityRecord activityRecord3 = prepareActivityRecord(startTime3, endTime3, 15, 15, user2, sportActivity1, 1000);
        activityRecordDAO.create(activityRecord3);

        LocalDateTime startTime4 = LocalDateTime.of(2018, 10, 28, 15, 0);
        LocalDateTime endTime4 = LocalDateTime.of(2018, 10, 28, 16, 0);
        ActivityRecord activityRecord4 = prepareActivityRecord(startTime4, endTime4, 15, 15, user2, sportActivity2, 1500);
        activityRecordDAO.create(activityRecord4);

        burnedCalories1 = prepareBurnedCalories(activityRecord1, user1, 80, 1000);

        burnedCalories2 = prepareBurnedCalories(activityRecord2, user1, 80, 1500);

        burnedCalories3 = prepareBurnedCalories(activityRecord3, user2, 55, 1000);

        burnedCalories4 = prepareBurnedCalories(activityRecord4, user2, 55, 1500);
    }

    @Test
    public void testCreate() {
        burnedCaloriesDAO.create(burnedCalories1);
        Assert.assertEquals(this.burnedCaloriesDAO.getById(burnedCalories1.getId()), burnedCalories1);
    }

    @Test
    public void testGetById() {
        entityManager.persist(burnedCalories1);
        entityManager.flush();
        BurnedCalories burnedCalories = burnedCaloriesDAO.getById(burnedCalories1.getId());
        Assert.assertEquals(burnedCalories1, burnedCalories);
    }

    @Test
    public void testGetAll() {
        entityManager.persist(burnedCalories1);
        entityManager.persist(burnedCalories2);
        entityManager.persist(burnedCalories3);
        entityManager.persist(burnedCalories4);
        entityManager.flush();
        Assert.assertEquals(burnedCaloriesDAO.getAll().size(), 4);
    }

    @Test
    public void testUpdate() {
        entityManager.persist(burnedCalories2);
        entityManager.flush();
        burnedCalories2.setActualWeight(100);
        burnedCalories2.setBurnedCalories(1200);
        burnedCaloriesDAO.update(burnedCalories2);
        Assert.assertEquals(burnedCaloriesDAO.getById(burnedCalories2.getId()), burnedCalories2);
    }

    @Test
    public void testDelete() {
        entityManager.persist(burnedCalories3);
        entityManager.flush();
        burnedCaloriesDAO.delete(burnedCalories3);
        Assert.assertNull(burnedCaloriesDAO.getById(burnedCalories3.getId()));
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateNull() {
        burnedCaloriesDAO.create(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateNull() {
        burnedCaloriesDAO.update(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testDeleteNull() {
        burnedCaloriesDAO.delete(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateWithNullUser() {
        burnedCalories4.setUser(null);
        burnedCaloriesDAO.create(burnedCalories4);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateWithNullActivityRecord() {
        burnedCalories4.setActivityRecord(null);
        burnedCaloriesDAO.create(burnedCalories4);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateWithZeroActualWeight() {
        burnedCalories4.setActualWeight(0);
        burnedCaloriesDAO.create(burnedCalories4);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateWithNegativeBurnedCalories() {
        burnedCalories4.setBurnedCalories(-1);
        burnedCaloriesDAO.create(burnedCalories4);
    }
}
