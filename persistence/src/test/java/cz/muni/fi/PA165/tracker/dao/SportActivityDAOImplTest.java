package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.PersistenceApplicationContext;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
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

/**
 * Tests for SportActivityDAOImpl class.
 * @author Dominik-Bujna
 *
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SportActivityDAOImplTest extends AbstractTestNGSpringContextTests {

    @Inject
    private SportActivityDAO sportActivityDAO;

    @PersistenceContext
    private EntityManager entityManager;

    private SportActivity activity1;
    private SportActivity activity2;
    private SportActivity activity3;
    private SportActivity activity4;

    @BeforeMethod
    public void setUp(){
        activity1 = new SportActivity();
        activity1.setActivityName("Football");
        activity1.setBurnedCaloriesPerHour(234);
        activity1.setWeightCoefficient(1.0);

        activity2 = new SportActivity();
        activity2.setActivityName("Beerpong");
        activity2.setBurnedCaloriesPerHour(15);
        activity2.setWeightCoefficient(1.0);

        activity3 = new SportActivity();
        activity3.setActivityName("Chess");
        activity3.setBurnedCaloriesPerHour(120);
        activity3.setWeightCoefficient(1.0);

        activity4 = new SportActivity();
        activity4.setActivityName("Cleaning up");
        activity4.setBurnedCaloriesPerHour(400);
        activity4.setWeightCoefficient(1.0);
    }

    @Test
    public void testCreate(){
        sportActivityDAO.create(activity1);
        Assert.assertEquals(this.sportActivityDAO.getById(activity1.getId()), activity1);
    }

    @Test
    public void testGetById(){
        entityManager.persist(activity1);
        entityManager.flush();
        SportActivity activity = sportActivityDAO.getById(activity1.getId());
        Assert.assertEquals(activity1, activity);

    }

    @Test
    public void testGetAll() {
        Assert.assertEquals(sportActivityDAO.getAll().size(), 0);
        entityManager.persist(activity1);
        entityManager.flush();
        entityManager.persist(activity2);
        entityManager.flush();
        entityManager.persist(activity3);
        entityManager.flush();
        entityManager.persist(activity4);
        entityManager.flush();
        Assert.assertEquals(sportActivityDAO.getAll().size(), 4);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateNull(){
        sportActivityDAO.create(null);
    }


    @Test(expectedExceptions = DataAccessException.class)
    public void testDeleteNonexistingSportActivity(){
        SportActivity activity = new SportActivity();
        sportActivityDAO.delete(activity);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testDeleteNullSportActivity(){
        sportActivityDAO.delete(null);
    }


    @Test
    public void testUpdate(){
        entityManager.persist(activity1);
        entityManager.flush();
        activity1.setActivityName("Bowling");
        activity1.setBurnedCaloriesPerHour(300);
        activity1.setWeightCoefficient(1.2);
        sportActivityDAO.update(activity1);
        Assert.assertEquals(sportActivityDAO.getById(activity1.getId()),activity1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateNull(){
        entityManager.persist(activity1);
        entityManager.flush();
        sportActivityDAO.update(null);
    }

    @Test
    public void testDelete(){
        entityManager.persist(activity4);
        entityManager.flush();
        sportActivityDAO.delete(activity4);
        Assert.assertNull(sportActivityDAO.getById(activity4.getId()));
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testActivityWithoutBurnedCal(){
        SportActivity incompleteActivity = new SportActivity();
        incompleteActivity.setActivityName("Feasting");
        incompleteActivity.setWeightCoefficient(1.0);
        sportActivityDAO.create(incompleteActivity);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testActivityWithoutName(){
        SportActivity incompleteActivity = new SportActivity();
        incompleteActivity.setWeightCoefficient(1.0);
        incompleteActivity.setBurnedCaloriesPerHour(420);
        sportActivityDAO.create(incompleteActivity);
    }
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testActivityWithNegativeBurnedCal(){
         SportActivity activity = new SportActivity();
        activity.setActivityName("Feasting");
        activity.setWeightCoefficient(1.0);
        activity.setBurnedCaloriesPerHour(-400);
        sportActivityDAO.create(activity);
    }
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testActivityWithNegativeCoefficient(){
        activity3.setWeightCoefficient(-5);
        sportActivityDAO.create(activity3);
    }
}