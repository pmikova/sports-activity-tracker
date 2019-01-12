package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.config.ServiceConfiguration;
import cz.muni.fi.PA165.tracker.dao.ActivityRecordDAO;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;
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
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * Activity Record Service Implementation tests.
 *
 * @author Dominik-Bujna
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ActivityRecordServiceImplTest  extends AbstractTestNGSpringContextTests {

    @Mock
    private ActivityRecordDAO activityRecordDAO;

    @Spy
    @Inject
    private MappingService mappingService;

    @InjectMocks
    private ActivityRecordService activityRecordService = new ActivityRecordServiceImpl();


    private ActivityRecord record1;
    private ActivityRecord record2;
    private ActivityRecord record3;
    private ActivityRecord record4;

    @BeforeMethod
    public void setUp(){
        record1 = new ActivityRecord();
        record1.setSportActivity(new SportActivity());
        record1.setUser(new User());
        record1.setStartTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 10, 00, 00));
        record1.setEndTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 11, 30, 00));
        record1.setDistance(15900);

        record2 = new ActivityRecord();
        record2.setSportActivity(new SportActivity());
        record2.setUser(new User());
        record2.setDuration(Duration.of(2, HOURS));
        record2.setStartTime(LocalDateTime.of(2018, Month.NOVEMBER, 11, 13, 00, 00));
        record2.setEndTime(LocalDateTime.of(2018, Month.NOVEMBER, 11, 14, 00, 00));
        record2.setDistance(1000);

        record3 = new ActivityRecord();
        record3.setSportActivity(new SportActivity());
        record3.setUser(new User());
        record3.setStartTime(LocalDateTime.of(2018, Month.NOVEMBER, 12, 10, 00, 00));
        record3.setEndTime(LocalDateTime.of(2018, Month.NOVEMBER, 13, 10, 00, 00));

        record4 = new ActivityRecord();
        record4.setSportActivity(new SportActivity());
        record4.setUser(new User());
        record4.setStartTime(LocalDateTime.of(2018, Month.NOVEMBER, 12, 10, 00, 00));
        record4.setDistance(15900);

        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void testCreate(){
//        activityRecordService.create(record1);
//        verify(activityRecordDAO).create(record1);
//    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNull(){
        activityRecordService.create(null);

    }


//    @Test
//    public void testUpdate(){
//        record1.setDistance(30);
//        activityRecordService.update(record1);
//        verify(activityRecordDAO).update(record1);
//    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNull(){
       activityRecordService.update(null);
    }

//    @Test
//    public void testDelete(){
//        activityRecordService.delete(record1);
//        verify(activityRecordDAO).delete(record1);
//    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNull(){
        activityRecordService.delete(null);
    }

    @Test
    public void testGetById(){
        when(activityRecordDAO.getById(1L)).thenReturn(record1);
        ActivityRecord record = activityRecordService.getById(1L);
        verify(activityRecordDAO).getById(1L);
        assertEquals(record, record1);
    }

    @Test
    public void testGetAll(){
        when(activityRecordDAO.getAll()).thenReturn(Arrays.asList(record1, record2, record3));
        List<ActivityRecord> records = activityRecordService.getAll();
        verify(activityRecordDAO).getAll();
        assertEquals(records, Arrays.asList(record1, record2, record3));
    }

    @Test
    public void testCalculateAverageSpeed(){
        double speed = activityRecordService.calculateAverageSpeed(record2);
        assertEquals(0.5, speed);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateAverageSpeedWithoutTime(){
        double speed = activityRecordService.calculateAverageSpeed(record4);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateAverageSpeedWithoutDistance(){
        double speed = activityRecordService.calculateAverageSpeed(record4);
        assertEquals(0.0, speed);
    }

    @Test
    public void testCalculateDuration(){
        Duration duration = activityRecordService.calculateDuration(record1);
        assertEquals(Duration.of(90, MINUTES), duration);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateDurationWithoutTime(){
        activityRecordService.calculateDuration(record4);
    }


}
