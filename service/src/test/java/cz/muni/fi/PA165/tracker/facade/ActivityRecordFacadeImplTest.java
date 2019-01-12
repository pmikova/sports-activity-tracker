package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.config.ServiceConfiguration;
import cz.muni.fi.PA165.tracker.dto.ActivityRecordCreateDTO;
import cz.muni.fi.PA165.tracker.dto.ActivityRecordDTO;
import cz.muni.fi.PA165.tracker.dto.ActivityRecordUpdateDTO;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.enums.Gender;
import cz.muni.fi.PA165.tracker.enums.UserType;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import cz.muni.fi.PA165.tracker.service.ActivityRecordService;
import cz.muni.fi.PA165.tracker.service.ActivityRecordServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
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
 * Activity Record Facade Implementation tests.
 *
 * @author Dominik-Bujna
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ActivityRecordFacadeImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ActivityRecordService activityRecordService;

    @Spy
    @Inject
    private MappingService mappingService;

    @InjectMocks
    private ActivityRecordFacade activityRecordFacade = new ActivityRecordFacadeImpl();

    private ActivityRecord record1;
    private ActivityRecord record2;
    private ActivityRecord record3;
    private User user;
    @BeforeMethod
    public void setUp(){


        user = new User(1L);
        user.setWeight(50);
        user.setEmail("email@gmail.com");
        user.setUserType(UserType.USER);
        user.setGender(Gender.FEMALE);
        user.setName("Lucy");
        user.setSurname("Strewn");
        user.setPasswordHash("password");
        user.setBirthdate(LocalDate.of(1999, 10, 15));

        record1 = new ActivityRecord();
        record1.setSportActivity(new SportActivity());
        record1.setUser(user);
        record1.setDuration(Duration.of(1, HOURS));
        record1.setStartTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 10, 00, 00));
        record1.setEndTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 11, 00, 00));
        record1.setDistance(15900);

        record2 = new ActivityRecord();
        record2.setSportActivity(new SportActivity());
        record2.setUser(user);
        record2.setDuration(Duration.of(1, HOURS));
        record2.setStartTime(LocalDateTime.of(2018, Month.NOVEMBER, 11, 13, 00, 00));
        record2.setEndTime(LocalDateTime.of(2018, Month.NOVEMBER, 11, 14, 00, 00));
        record2.setDistance(15900);
        record2.setId(9L);

        record3 = new ActivityRecord();
        record3.setSportActivity(new SportActivity());
        record3.setUser(user);
        record3.setDuration(Duration.of(1, HOURS));
        record3.setStartTime(LocalDateTime.of(2018, Month.NOVEMBER, 12, 10, 00, 00));
        record3.setEndTime(LocalDateTime.of(2018, Month.NOVEMBER, 13, 10, 00, 00));
        record3.setDistance(15900);


        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void testCreate(){
//        ActivityRecordCreateDTO activityRecordCreateDTO = mappingService.mapTo(record1, ActivityRecordCreateDTO.class);
//        activityRecordFacade.create(activityRecordCreateDTO);
//        verify(activityRecordService).create(any(ActivityRecord.class));
//    }
//
//    @Test
//    public void testUpdate(){
//        when(activityRecordService.getById(9L)).thenReturn(record2);
//        ActivityRecordUpdateDTO activityRecordDTO = mappingService.mapTo(record2, ActivityRecordUpdateDTO.class);
//        activityRecordDTO.setEndTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 10, 30, 00));
//        activityRecordFacade.update(activityRecordDTO);
//        verify(activityRecordService).update(any(ActivityRecord.class));
//    }

    @Test
    public void testDelete(){
        ActivityRecordDTO activityRecordDTO = mappingService.mapTo(record1, ActivityRecordDTO.class);
        activityRecordFacade.delete(activityRecordDTO);
        verify(activityRecordService).delete(any(ActivityRecord.class));
    }

    @Test
    public void testGetById(){
        when(activityRecordService.getById(record1.getId())).thenReturn(record1);
        ActivityRecordDTO activityRecordDTO = activityRecordFacade.getById(record1.getId());
        verify(activityRecordService).getById(record1.getId());
        assertEquals(record1, mappingService.mapTo(activityRecordDTO, ActivityRecord.class));

    }
    @Test
    public void testGetAll(){
        when(activityRecordService.getAll()).thenReturn(Arrays.asList(record1, record2, record3));
        List<ActivityRecordDTO> ansDTO = activityRecordFacade.getAll();
        verify(activityRecordService).getAll();
        assertEquals(3, ansDTO.size());
        List<ActivityRecord> ans = mappingService.mapTo(ansDTO, ActivityRecord.class);
        assertEquals(3, ans.size());
        assertTrue(ans.contains(record1));
        assertTrue(ans.contains(record2));
        assertTrue(ans.contains(record3));
    }
}
