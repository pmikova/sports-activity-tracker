package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.config.ServiceConfiguration;
import cz.muni.fi.PA165.tracker.dto.UserAuthenticationDTO;
import cz.muni.fi.PA165.tracker.dto.UserDTO;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.enums.Gender;
import cz.muni.fi.PA165.tracker.enums.UserType;
import cz.muni.fi.PA165.tracker.exceptions.NotExistingEntityException;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import cz.muni.fi.PA165.tracker.mapping.MappingServiceImpl;
import cz.muni.fi.PA165.tracker.service.UserService;
import cz.muni.fi.PA165.tracker.facade.UserFacade;
import cz.muni.fi.PA165.tracker.facade.UserFacadeImpl;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import java.time.LocalDate;
import java.util.*;
import javax.inject.Inject;
import org.mockito.*;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.testng.annotations.BeforeClass;
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
        when(userService.isAdministrator(admin)).thenReturn(true);
        when(userService.isAdministrator(user)).thenReturn(false);

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
        Assert.assertTrue(userFacade.isAdministrator(adminDTO));

    }

    @Test
    public void isUserAdministratorTest() {
        Assert.assertFalse(userFacade.isAdministrator(userDTO));

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void isAdministratorNullTest() {
        userFacade.isAdministrator(null);


    }

    @Test
    public void findByNameTest() {
        UserDTO a = userFacade.getByEmail(admin.getEmail());
        assertEquals(adminDTO, a);
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
        userFacade.delete(adminDTO);
        verify(userService).delete(admin);
    }

}
