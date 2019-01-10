package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.config.ServiceConfiguration;
import cz.muni.fi.PA165.tracker.dto.*;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.BurnedCalories;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.enums.Gender;
import cz.muni.fi.PA165.tracker.enums.UserType;
import cz.muni.fi.PA165.tracker.exceptions.NotExistingEntityException;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import cz.muni.fi.PA165.tracker.mapping.MappingServiceImpl;
import cz.muni.fi.PA165.tracker.service.UserService;
import cz.muni.fi.PA165.tracker.service.UserStatService;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import javax.inject.Inject;
import org.mockito.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * User Facade Implementation tests.
 * @author pmikova 433345
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserFacadeImplTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private final UserFacade userFacade = new UserFacadeImpl();

    @Mock
    private UserService userService;

    @Mock
    private UserStatService userStatService;

    @Spy
    @Inject
    private final MappingService mappingService = new MappingServiceImpl();

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Captor
    ArgumentCaptor<String> strCaptor;

    User user;
    UserDTO userDTO;
    User admin;
    UserDTO adminDTO;
    User noid;
    UserDTO noidDTO;
    UserAuthenticationDTO authDTO;


    @BeforeMethod
    public void init() {

        user = new User(1L);
        user.setWeight(50);
        user.setEmail("email@gmail.com");
        user.setUserType(UserType.USER);
        user.setGender(Gender.FEMALE);
        user.setName("Lucy");
        user.setSurname("Strewn");
        user.setPasswordHash("password");
        user.setBirthdate(LocalDate.of(1999, 10, 15));


        admin = new User(2L);
        admin.setWeight(80);
        admin.setEmail("rmail@gmail.com");
        admin.setUserType(UserType.ADMIN);
        admin.setGender(Gender.MALE);
        admin.setName("Othello");
        admin.setSurname("Brown");
        admin.setPasswordHash("passpasspass");
        admin.setBirthdate(LocalDate.of(1998, 12, 01));

        userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setWeight(user.getWeight());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserType(user.getUserType());
        userDTO.setGender(user.getGender());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setPasswordHash(user.getPasswordHash());
        userDTO.setBirthdate(user.getBirthdate());

        adminDTO = new UserDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setWeight(admin.getWeight());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setUserType(admin.getUserType());
        adminDTO.setGender(admin.getGender());
        adminDTO.setName(admin.getName());
        adminDTO.setSurname(admin.getSurname());
        adminDTO.setPasswordHash(admin.getPasswordHash());
        adminDTO.setBirthdate(admin.getBirthdate());

        noid = new User();
        noid.setWeight(30);
        noid.setEmail("smail@gmail.com");
        noid.setUserType(UserType.USER);
        noid.setGender(Gender.MALE);
        noid.setName("Olaf");
        noid.setSurname("Bjorndalen");
        noid.setPasswordHash("hahahahahaha");
        noid.setBirthdate(LocalDate.of(1920, 12, 18));

        noidDTO = new UserDTO();
        noidDTO.setId(noid.getId());
        noidDTO.setWeight(noid.getWeight());
        noidDTO.setEmail(noid.getEmail());
        noidDTO.setUserType(noid.getUserType());
        noidDTO.setGender(noid.getGender());
        noidDTO.setName(noid.getName());
        noidDTO.setSurname(noid.getSurname());
        noidDTO.setPasswordHash(noid.getPasswordHash());
        noidDTO.setBirthdate(noid.getBirthdate());

        authDTO = new UserAuthenticationDTO();
        authDTO.setId(user.getId());
        authDTO.setPasswordHash(user.getPasswordHash());



    }

    @BeforeMethod(dependsOnMethods = "init")
    public void initMocksBehaviour() {
        MockitoAnnotations.initMocks(this);
        when(userService.isAdministrator(admin.getId())).thenReturn(true);
        when(userService.isAdministrator(user.getId())).thenReturn(false);

        when(userService.getById(Long.valueOf(20L))).thenReturn(null);
        when(userService.getById(1L)).thenReturn(user);
        when(userService.getById(2L)).thenReturn(admin);

        when(userService.getByEmail(user.getEmail())).thenReturn(user);
        when(userService.getByEmail(admin.getEmail())).thenReturn(admin);

    }
    @Test
    public void classInitTest() {
        assertNotNull(mappingService);
        assertNotNull(userFacade);
        assertNotNull(userService);
    }

    @Test(expectedExceptions = NotExistingEntityException.class)
    public void getNotExistingUserByIdTest() {
        userFacade.getById(20L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTest() {
        userFacade.create(null);
    }

    @Test
    public void createTest() {
        userFacade.create(userDTO);
        verify(userService).register(userCaptor.capture(), strCaptor.capture());
        assertEquals(userCaptor.getValue(), user);
        assertEquals(strCaptor.getValue(), userDTO.getPasswordHash());
    }


    @Test
    public void createAdminTest() {
        userFacade.create(adminDTO);
        verify(userService).register(userCaptor.capture(), strCaptor.capture());
        assertEquals(userCaptor.getValue(), admin);
        assertEquals(strCaptor.getValue(), adminDTO.getPasswordHash());
    }

    @Test(expectedExceptions = NotExistingEntityException.class)
    public void logInNullId() {
        userFacade.logIn(null);
    }

    @Test
    public void logInTest() {
        userFacade.logIn(authDTO);
        verify(userService).authenticate(userCaptor.capture(), strCaptor.capture());
        assertEquals(userCaptor.getValue().getId(), user.getId());
        assertEquals(userCaptor.getValue().getPasswordHash(), user.getPasswordHash());
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTest() {
        userFacade.delete(null);
    }

    @Test
    public void getByIdTest() {
        UserDTO u = userFacade.getById(user.getId());
        assertEquals(u, userDTO);
    }

    @Test
    public void isAdministratorTest() {
        Assert.assertTrue(userFacade.isAdministrator(adminDTO.getId()));

    }

    @Test
    public void isUserAdministratorTest() {
        Assert.assertFalse(userFacade.isAdministrator(userDTO.getId()));

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void isAdministratorNullTest() {
        userFacade.isAdministrator(null);


    }
    @Test
    public void testGetAll(){
        when(userService.getAll()).thenReturn(Collections.singletonList(admin));
        userFacade.getAll();
        verify(userService).getAll();
    }

    @Test(expectedExceptions = NotExistingEntityException.class)
    public void getNotExistingTest() {
        userFacade.getById(20L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getByIdNullTest() {
        userFacade.getById(null);
    }

    @Test
    public void getByEmailTest() {
        UserDTO result = userFacade.getByEmail(user.getEmail());
        assertEquals(result.getId(), user.getId());
        assertEquals(result.getEmail(), user.getEmail());
        assertEquals(result.getPasswordHash(), user.getPasswordHash());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getByEmailNullTest() {
        userFacade.getByEmail(null);
    }

    @Test(expectedExceptions = NotExistingEntityException.class)
    public void getNonExistingByEmailTest() {
        userFacade.getByEmail("hi@hi.hi");
    }

    @Test
    public void deleteTest() {
        userFacade.delete(userDTO);
        verify(userService).delete(user);
    }
    @Test
    public void deleteAdminTest() {
        userFacade.delete(adminDTO);
        verify(userService).delete(admin);
    }


    @Test
    public void getStatisticsTest() {
        SportActivity activity1 =  new SportActivity();
        activity1.setActivityName("running");
        activity1.setWeightCoefficient(1.3);
        activity1.setBurnedCaloriesPerHour(120);

        SportActivity activity2 = new SportActivity();
        activity2.setBurnedCaloriesPerHour(300);
        activity2.setActivityName("hiding");
        activity2.setWeightCoefficient(1.1);

        ActivityRecord record1 = new ActivityRecord();
        record1.setUser(user);
        record1.setDistance(30);
        record1.setStartTime(LocalDate.now().minusDays(1).atTime(10, 10));
        record1.setEndTime(LocalDate.now().minusDays(1).atTime(12, 12));
        record1.setSportActivity(activity1);
        record1.setAverageSpeed(12);
        record1.setDuration(Duration.between(LocalDate.now().minusDays(1).atTime(10, 10),
                LocalDate.now().minusDays(1).atTime(12, 12) ));

        ActivityRecord record2 = new ActivityRecord();
        record2.setUser(user);
        record2.setDistance(10);
        record2.setStartTime(LocalDate.now().minusDays(20).atTime(11, 12));
        record2.setEndTime(LocalDate.now().minusDays(20).atTime(13, 14));
        record2.setSportActivity(activity2);
        record2.setAverageSpeed(8);
        record2.setDuration(Duration.between(LocalDate.now().minusDays(20).atTime(11, 12),
                LocalDate.now().minusDays(20).atTime(13, 14) ));

        SportActivityDTO activityDTO = new SportActivityDTO();
        activityDTO.setActivityName("running");
        activityDTO.setWeightCoefficient(1.3);
        activityDTO.setBurnedCaloriesPerHour(120);

        SportActivityDTO activity2DTO = new SportActivityDTO();
        activity2DTO.setBurnedCaloriesPerHour(300);
        activity2DTO.setActivityName("hiding");
        activity2DTO.setWeightCoefficient(1.1);

        BurnedCalories cals1 = new BurnedCalories();
        cals1.setActivityRecordId(record1.getId());
        cals1.setActualWeight(87);
        cals1.setUser(user);
        cals1.setBurnedCalories(1000);

        BurnedCalories cals2 = new BurnedCalories();
        cals2.setActivityRecordId(record2.getId());
        cals2.setActualWeight(45);
        cals2.setUser(user);
        cals2.setBurnedCalories(199);

        LocalDate startMonth = LocalDate.now().minusMonths(1);
        LocalDate startWeek = LocalDate.now().minusWeeks(1);
        LocalDate now = LocalDate.now();

        ArrayList<ActivityRecord> activityList = new ArrayList<>();
        activityList.add(record1);
        activityList.add(record2);

        ArrayList<BurnedCalories> burnedList = new ArrayList<>();
        burnedList.add(cals1);
        burnedList.add(cals2);

        when(userStatService.getAllCalories(user)).thenReturn(cals1.getBurnedCalories() + cals2.getBurnedCalories());
        when(userStatService.getAllCalories(user, startWeek, now)).thenReturn(cals1.getBurnedCalories());
        when(userStatService.getAllCalories(user, startMonth, now)).thenReturn(cals1.getBurnedCalories() + cals2.getBurnedCalories());


        when(userStatService.getNumberOfActivities(user)).thenReturn(activityList.size());
        when(userStatService.getNumberOfActivities(user, startWeek, now)).thenReturn(1);
        when(userStatService.getNumberOfActivities(user, startMonth, now)).thenReturn(3);

        HashMap<SportActivity, Integer> activities = new HashMap<>();
        activities.put(activity1, 2);
        activities.put(activity2, 4);

        when(userStatService.countActivitiesForUser(user)).thenReturn(activities);

        UserStatDTO userStatDTO = userFacade.getStats(userDTO);
        Assert.assertEquals(userStatDTO.getCaloriesMonth(), cals1.getBurnedCalories() + cals2.getBurnedCalories());
        Assert.assertEquals(userStatDTO.getCalories(), cals1.getBurnedCalories() + cals2.getBurnedCalories());
        Assert.assertEquals(userStatDTO.getCaloriesWeek(), cals1.getBurnedCalories());
        Assert.assertEquals(userStatDTO.getActivities(), activityList.size());
        Assert.assertEquals(userStatDTO.getActivitiesSumUp().size(), activities.size());

        Assert.assertTrue(userStatDTO.getActivitiesSumUp().containsKey(activityDTO));
        Assert.assertTrue(userStatDTO.getActivitiesSumUp().containsKey(activity2DTO));

        Assert.assertEquals(userStatDTO.getActivitiesSumUp().get(activityDTO), Integer.valueOf(2));
        Assert.assertEquals(userStatDTO.getActivitiesSumUp().get(activity2DTO), Integer.valueOf(4));

    }
}
