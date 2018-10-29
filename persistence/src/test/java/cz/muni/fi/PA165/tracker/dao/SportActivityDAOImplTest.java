package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.PersistenceApplicationContext;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
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







}
