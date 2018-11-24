package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import cz.muni.fi.PA165.tracker.service.ActivityRecordService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import javax.inject.Inject;

/**
 * Activity Record Facade Implementation tests.
 * @author TODO
 */
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

    @BeforeMethod
    public void setUp(){

    }









    api/src/main/java/cz/muni/fi/PA165/tracker/dto/ActivityRecordCreateDTO.java
    service/src/main/java/cz/muni/fi/PA165/tracker/service/ActivityRecordServiceImpl.java
    service/src/test/java/cz/muni/fi/PA165/tracker/facade/ActivityRecordFacadeImplTest.java






}
