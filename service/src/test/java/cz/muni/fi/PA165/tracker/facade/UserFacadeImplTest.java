package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.config.ServiceConfiguration;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
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


    @BeforeClass
    public void setupMockito() {
        MockitoAnnotations.initMocks(this);
    }

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


    }

    @BeforeMethod(dependsOnMethods = "init")
    public void initMocksBehaviour() {
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
    public void findNonExistingUserByIdTest() {
        userFacade.getById(20L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTest() {
        userFacade.create(null);
    }
/*
    @Test
    public void createTest() {
        userFacade.create(userDTO);
        verify(userService).register(userCaptor.capture(), strCaptor.capture());
        assertEquals(userCaptor.getValue(), user);
        assertEquals(strCaptor.getValue(), userDTO.getPasswordHash());
    }
*/

    @Test
    public void createAdminTest() {
        Long id = userFacade.create(adminDTO);
        verify(userService).register(userCaptor.capture(), strCaptor.capture());
        assertEquals(userCaptor.getValue(), admin);
        assertEquals(strCaptor.getValue(), adminDTO.getPasswordHash());
    }
/*
    @Test
    public void deleteUserTest() {
        userFacade.delete(userDTO);
        verify(userService).delete(userCaptor.capture());
        assertEquals(userCaptor.getValue().getId(), user.getId());
        assertEquals(userCaptor.getValue().getEmail(), user.getEmail());
    }
*/
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTest() {
        userFacade.delete(null);
    }

    @Test
    public void findUserByIdTest() {
        UserDTO u = userFacade.getById(user.getId());
        assertEquals(u, userDTO);
    }

/*
    @Test(expectedExceptions = NotExistingEntityException.class)
    public void removeUserNonExistingTest() {
        userDTO.setId(20L);
        userFacade.delete(userDTO);
    }
*/
    @Test
    public void isAdministratorTest() {
        Assert.assertTrue(userFacade.isAdministrator(adminDTO));

    }

    @Test
    public void isUserAdministratorTest() {
        Assert.assertFalse(userFacade.isAdministrator(userDTO));

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void isAdminNullTest() {
        userFacade.isAdministrator(null);


    }

    @Test
    public void findByNameTest() {
        UserDTO a = userFacade.getByEmail(admin.getEmail());
        assertEquals(adminDTO, a);
    }










}
