package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.dto.ActivityRecordCreateDTO;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import cz.muni.fi.PA165.tracker.service.ActivityRecordService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

import static java.time.temporal.ChronoUnit.HOURS;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Activity Record Facade Implementation tests.
 *
 * @author Dominik-Bujna
 */
public class ActivityRecordFacadeImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ActivityRecordService activityRecordService;

    @Spy
    @Inject
    private MappingService mappingService;

    @InjectMocks
    private ActivityRecordFacade activityRecordFacade;

    private ActivityRecord record1;
    private ActivityRecord record2;
    private ActivityRecord record3;

    @BeforeMethod
    public void setUp(){
        record1.setSportActivity(new SportActivity());
        record1.setUser(new User());
        record1.setDuration(Duration.of(1, HOURS));
        record1.setStartTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 10, 00, 00));
        record1.setEndTime(LocalDateTime.of(2018, Month.NOVEMBER, 10, 11, 00, 00));
        record1.setDistance(15900);



        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate(){
        ActivityRecordCreateDTO activityRecordCreateDTO = mappingService.mapTo(record1, ActivityRecordCreateDTO.class);
        activityRecordFacade.create(activityRecordCreateDTO);
        verify(activityRecordService).update(any(ActivityRecord.class));
    }





}
