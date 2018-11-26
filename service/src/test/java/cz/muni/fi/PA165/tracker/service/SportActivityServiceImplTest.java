package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.config.ServiceConfiguration;
import cz.muni.fi.PA165.tracker.dao.SportActivityDAO;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * Sport Activity Service Implementation tests.
 *
 * @author Dominik-Bujna
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class SportActivityServiceImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private SportActivityDAO activityDAO;

    @Spy
    @Inject
    private MappingService mappingService;

    @InjectMocks
    private SportActivityService activityService = new SportActivityServiceImpl();

    private SportActivity activity1;
    private SportActivity activity2;
    private SportActivity activity3;

    @BeforeMethod
    public void setup(){
        activity1 = new SportActivity();
        activity1.setActivityName("Waterboarding");
        activity1.setWeightCoefficient(2.0);
        activity1.setBurnedCaloriesPerHour(400.0);

        activity2 = new SportActivity();
        activity2.setActivityName("Dumpster diving");
        activity2.setWeightCoefficient(1.5);
        activity2.setBurnedCaloriesPerHour(420.0);

        activity3 = new SportActivity();
        activity3.setActivityName("Segway-ing");
        activity3.setWeightCoefficient(1.2);
        activity3.setBurnedCaloriesPerHour(30);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate(){
        activityService.create(activity1);
        verify(activityDAO).create(activity1);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNull(){
        activityService.create(null);
    }


    @Test
    public void testUpdate(){
        activityService.update(activity1);
        verify(activityDAO).update(activity1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNull(){
        activityService.update(null);
    }

    @Test
    public void testDelete(){
        activityService.delete(activity1);
        verify(activityDAO).delete(activity1);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNull(){
        activityService.delete(null);
    }

    @Test
    public void testGetById(){
        when(activityDAO.getById(1L)).thenReturn(activity1);
        SportActivity activity = activityService.getById(1L);
        verify(activityDAO).getById(1L);
        assertEquals(activity, activity1);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetByIdNull(){
        activityService.getById(null);
    }

    @Test
    public void testGetAll(){
        when(activityDAO.getAll()).thenReturn(Arrays.asList(activity1, activity2, activity3));
        List<SportActivity> activity = activityService.getAll();
        verify(activityDAO).getAll();
        assertEquals(activity, Arrays.asList(activity1, activity2, activity3));
    }

}
