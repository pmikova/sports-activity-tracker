package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.config.ServiceConfiguration;
import cz.muni.fi.PA165.tracker.dto.ActivityRecordDTO;
import cz.muni.fi.PA165.tracker.dto.BurnedCaloriesCreateDTO;
import cz.muni.fi.PA165.tracker.dto.BurnedCaloriesDTO;
import cz.muni.fi.PA165.tracker.dto.UserDTO;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.BurnedCalories;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.enums.Gender;
import cz.muni.fi.PA165.tracker.enums.UserType;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import cz.muni.fi.PA165.tracker.mapping.MappingServiceImpl;
import cz.muni.fi.PA165.tracker.service.BurnedCaloriesService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Burned Calories Facade Implementation tests.
 * @author HonzaOstrava
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BurnedCaloriesFacadeImplTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private final BurnedCaloriesFacade burnedCaloriesFacade = new BurnedCaloriesFacadeImpl();

    @Mock
    private BurnedCaloriesService burnedCaloriesService;

    @Spy
    @Inject
    private MappingService mappingService = new MappingServiceImpl();

    private BurnedCalories burnedCalories1;
    private BurnedCalories burnedCalories2;

    private User user;

    private ActivityRecord activityRecord;

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

        activityRecord = new ActivityRecord();
        activityRecord.setSportActivity(new SportActivity());
        activityRecord.setUser(user);
        activityRecord.setDuration(Duration.of(1, HOURS));
        activityRecord.setStartTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 10, 0, 0));
        activityRecord.setEndTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 11, 0, 0));
        activityRecord.setDistance(15900);

        burnedCalories1 = new BurnedCalories();
        burnedCalories1.setActivityRecordId(activityRecord.getId());
        burnedCalories1.setUser(user);
        burnedCalories1.setActualWeight(user.getWeight());

        burnedCalories2 = new BurnedCalories();
        burnedCalories2.setActivityRecordId(999L);
        burnedCalories2.setUser(new User());
        burnedCalories2.setActualWeight(60);
    }

    @Test
    public void testCreate() {
        BurnedCaloriesCreateDTO burnedCaloriesCreateDTO = mappingService.mapTo(burnedCalories2, BurnedCaloriesCreateDTO.class);
        burnedCaloriesFacade.create(burnedCaloriesCreateDTO);
        verify(burnedCaloriesService).create(any(BurnedCalories.class));
    }

    @Test
    public void testUpdate() {
        BurnedCaloriesDTO burnedCaloriesDTO = mappingService.mapTo(burnedCalories2, BurnedCaloriesDTO.class);
        burnedCaloriesDTO.setActualWeight(70);
        burnedCaloriesFacade.update(burnedCaloriesDTO);
        verify(burnedCaloriesService).update(any(BurnedCalories.class));
    }

    @Test
    public void testDelete() {
        BurnedCaloriesDTO burnedCaloriesDTO = mappingService.mapTo(burnedCalories2, BurnedCaloriesDTO.class);
        burnedCaloriesFacade.delete(burnedCaloriesDTO);
        verify(burnedCaloriesService).delete(any(BurnedCalories.class));
    }
/*
    @Test
    public void testGetById() {
        when(burnedCaloriesService.getById(burnedCalories2.getId())).thenReturn(burnedCalories2);
        BurnedCaloriesDTO burnedCaloriesDTO = burnedCaloriesFacade.getById(burnedCalories2.getId());
        verify(burnedCaloriesService).getById(burnedCalories2.getId());
        assertEquals(burnedCalories2, mappingService.mapTo(burnedCaloriesDTO, BurnedCalories.class));
    }

    @Test
    public void testGetAll() {
        when(burnedCaloriesService.getAll()).thenReturn(Arrays.asList(burnedCalories1, burnedCalories2));
        List<BurnedCaloriesDTO> burnedCaloriesDTOs = burnedCaloriesFacade.getAll();
        verify(burnedCaloriesService).getAll();
        assertEquals(2, burnedCaloriesDTOs.size());
        List<BurnedCalories> burnedCalories = mappingService.mapTo(burnedCaloriesDTOs, BurnedCalories.class);
        assertEquals(2, burnedCalories.size());
        assertTrue(burnedCalories.contains(burnedCalories1));
        assertTrue(burnedCalories.contains(burnedCalories2));
    }
*/
    @Test
    public void testGetByUser() {
        when(burnedCaloriesService.getByUser(user)).thenReturn(Arrays.asList(burnedCalories1));
        UserDTO userDTO = mappingService.mapTo(user, UserDTO.class);
        List<BurnedCaloriesDTO> burnedCaloriesDTOs = burnedCaloriesFacade.getByUser(userDTO);
        verify(burnedCaloriesService).getByUser(user);
        assertEquals(1, burnedCaloriesDTOs.size());
        List<BurnedCalories> burnedCalories = mappingService.mapTo(burnedCaloriesDTOs, BurnedCalories.class);
        assertEquals(1, burnedCalories.size());
        assertTrue(burnedCalories.contains(burnedCalories1));
    }
/*
    @Test
    public void testGetByActivity() {
        when(burnedCaloriesService.getByActivity(activityRecord)).thenReturn(Arrays.asList(burnedCalories1));
        ActivityRecordDTO activityRecordDTO = mappingService.mapTo(activityRecord, ActivityRecordDTO.class);
        List<BurnedCaloriesDTO> burnedCaloriesDTOs = burnedCaloriesFacade.getByActivity(activityRecordDTO);
        verify(burnedCaloriesService).getByActivity(activityRecord);
        assertEquals(1, burnedCaloriesDTOs.size());
        List<BurnedCalories> burnedCalories = mappingService.mapTo(burnedCaloriesDTOs, BurnedCalories.class);
        assertEquals(1, burnedCalories.size());
        assertTrue(burnedCalories.contains(burnedCalories1));
    }*/
}
