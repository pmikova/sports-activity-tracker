package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.config.ServiceConfiguration;
import cz.muni.fi.PA165.tracker.dto.SportActivityCreateDTO;
import cz.muni.fi.PA165.tracker.dto.SportActivityDTO;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import cz.muni.fi.PA165.tracker.service.SportActivityService;
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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Sport Activity Facade Implementation tests.
 *
 * @author Dominik-Bujna
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class SportActivityFacadeImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private SportActivityService sportActivityService;

    @Spy
    @Inject
    private MappingService mappingService;

    @InjectMocks
    private SportActivityFacade sportActivityFacade = new SportActivityFacadeImpl();

    private SportActivity activity1;
    private SportActivity activity2;
    private SportActivity activity3;

    @BeforeMethod
    public void setUp() {
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
    public void testCreate() {
        SportActivityCreateDTO activityCreateDTO = mappingService.mapTo(activity1, SportActivityCreateDTO.class);
        sportActivityFacade.create(activityCreateDTO);
        verify(sportActivityService).create(any(SportActivity.class));
    }

    @Test
    public void testUpdate() {
        SportActivityDTO activityDTO = mappingService.mapTo(activity1, SportActivityDTO.class);
        activityDTO.setActivityName("Snowboarding");
        sportActivityFacade.update(activityDTO);
        verify(sportActivityService).update(any(SportActivity.class));
    }

    @Test
    public void testDelete() {
        SportActivityDTO activityDTO = mappingService.mapTo(activity1, SportActivityDTO.class);
        sportActivityFacade.delete(activityDTO);
        verify(sportActivityService).delete(any(SportActivity.class));
    }

    @Test
    public void testGetById() {
        when(sportActivityService.getById(activity1.getId())).thenReturn(activity1);
        SportActivityDTO activityDTO = sportActivityFacade.getById(activity1.getId());
        verify(sportActivityService).getById(activity1.getId());
        assertEquals(activity1, mappingService.mapTo(activityDTO, SportActivity.class));
    }

    @Test
    public void testGetAll() {
        when(sportActivityService.getAll()).thenReturn(Arrays.asList(activity1, activity2, activity3));
        List<SportActivityDTO> ansDTO = sportActivityFacade.getAll();
        verify(sportActivityService).getAll();
        assertEquals(3, ansDTO.size());
        List<SportActivity> ans = mappingService.mapTo(ansDTO, SportActivity.class);
        assertEquals(3, ans.size());
        assertTrue(ans.contains(activity1));
        assertTrue(ans.contains(activity2));
        assertTrue(ans.contains(activity3));
    }


}
