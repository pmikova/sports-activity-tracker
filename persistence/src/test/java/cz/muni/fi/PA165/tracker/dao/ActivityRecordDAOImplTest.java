package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.PersistenceApplicationContext;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
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
 * Tests for ActivityRecordDAOImpl class.
 * @author HonzaOstrava
 *
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ActivityRecordDAOImplTest extends AbstractTestNGSpringContextTests {

    @Inject
    ActivityRecordDAO activityRecordDAO;
    @Inject
    UserDAO userDAO;
    @Inject
    SportActivityDAO sportActivityDAO;

    @PersistenceContext
    private EntityManager entityManager;

    private ActivityRecord activityRecord1;
    private ActivityRecord activityRecord2;
    private ActivityRecord activityRecord3;
    private ActivityRecord activityRecord4;

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

    private ActivityRecord prepareActivityRecord(LocalDateTime startTime, LocalDateTime endTime, double averageSpeed, int distance, User user, SportActivity sportActivity) {
        ActivityRecord activityRecord = new ActivityRecord();
        activityRecord.setStartTime(startTime);
        activityRecord.setEndTime(endTime);
        activityRecord.setDuration(Duration.between(startTime, endTime));
        activityRecord.setAverageSpeed(averageSpeed);
        activityRecord.setDistance(distance);
        activityRecord.setUser(user);
        activityRecord.setSportActivity(sportActivity);
        return activityRecord;
    }

    @BeforeMethod
    public void setUp() {
        LocalDate birthdate1 = LocalDate.of(1950,10,13);
        User user1 = prepareUser("johnd@email.com", "John", "Doe", Gender.MALE, "password", 90, birthdate1, UserType.USER);
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
        activityRecord1 = prepareActivityRecord(startTime1, endTime1, 15, 15, user1, sportActivity1);

        LocalDateTime startTime2 = LocalDateTime.of(2018, 10, 26, 15, 0);
        LocalDateTime endTime2 = LocalDateTime.of(2018, 10, 26, 16, 0);
        activityRecord2 = prepareActivityRecord(startTime2, endTime2, 15, 15, user1, sportActivity2);

        LocalDateTime startTime3 = LocalDateTime.of(2018, 10, 27, 15, 0);
        LocalDateTime endTime3 = LocalDateTime.of(2018, 10, 27, 16, 0);
        activityRecord3 = prepareActivityRecord(startTime3, endTime3, 15, 15, user2, sportActivity1);

        LocalDateTime startTime4 = LocalDateTime.of(2018, 10, 28, 15, 0);
        LocalDateTime endTime4 = LocalDateTime.of(2018, 10, 28, 16, 0);
        activityRecord4 = prepareActivityRecord(startTime4, endTime4, 15, 15, user2, sportActivity2);
    }

    @Test
    public void testCreate() {
        activityRecordDAO.create(activityRecord1);
        Assert.assertEquals(activityRecordDAO.getById(activityRecord1.getId()), activityRecord1);
    }

    @Test
    public void testGetById() {
        entityManager.persist(activityRecord1);
        entityManager.flush();
        ActivityRecord activityRecord = activityRecordDAO.getById(activityRecord1.getId());
        Assert.assertEquals(activityRecord, activityRecord);
    }

    @Test
    public void testGetAll() {
        entityManager.persist(activityRecord1);
        entityManager.persist(activityRecord2);
        entityManager.persist(activityRecord3);
        entityManager.persist(activityRecord4);
        entityManager.flush();
        Assert.assertEquals(activityRecordDAO.getAll().size(), 4);
    }

    @Test
    public void testUpdate() {
        entityManager.persist(activityRecord2);
        entityManager.flush();
        activityRecord2.setDistance(30);
        activityRecord2.setAverageSpeed(30);
        activityRecordDAO.update(activityRecord2);
        Assert.assertEquals(activityRecordDAO.getById(activityRecord2.getId()), activityRecord2);
    }

    @Test
    public void testDelete() {
        entityManager.persist(activityRecord3);
        entityManager.flush();
        activityRecordDAO.delete(activityRecord3);
        Assert.assertNull(activityRecordDAO.getById(activityRecord3.getId()));
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateNull() {
        activityRecordDAO.create(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNull() {
        activityRecordDAO.update(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testDeleteNull() {
        activityRecordDAO.delete(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateWithNullUser() {
        activityRecord4.setUser(null);
        activityRecordDAO.create(activityRecord4);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateWithNullSportActivity() {
        activityRecord4.setSportActivity(null);
        activityRecordDAO.create(activityRecord4);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateWithNullDuration() {
        activityRecord4.setDuration(null);
        activityRecordDAO.create(activityRecord4);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateWithNullStartTime() {
        activityRecord4.setStartTime(null);
        activityRecordDAO.create(activityRecord4);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateWithNullEndTime() {
        activityRecord4.setEndTime(null);
        activityRecordDAO.create(activityRecord4);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateWithNegativeDistance() {
        activityRecord4.setDistance(-1);
        activityRecordDAO.create(activityRecord4);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateWithNegativeAverageSpeed() {
        activityRecord4.setAverageSpeed(-1);
        activityRecordDAO.create(activityRecord4);
    }
}
