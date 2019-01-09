package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.config.ServiceConfiguration;
import cz.muni.fi.PA165.tracker.dao.ActivityRecordDAO;
import cz.muni.fi.PA165.tracker.dao.BurnedCaloriesDAO;
import cz.muni.fi.PA165.tracker.dao.UserDAO;
import cz.muni.fi.PA165.tracker.dto.UserDTO;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.enums.Gender;
import cz.muni.fi.PA165.tracker.enums.UserType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mockito.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * User service tests.
 * @author pmikova 433345
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserServiceImplTest extends AbstractTestNGSpringContextTests {


    @Mock
    private UserDAO userDAO;

    @Mock
    private ActivityRecordDAO activityRecordDAO;

    @Mock
    private BurnedCaloriesDAO bcd;

    @InjectMocks
    private final UserService userService = new UserServiceImpl();

    @Captor
    ArgumentCaptor<User> userCaptor;


    User user;
    User admin;
    UserDTO userDTO;
    UserDTO adminDTO;
    User noid;
    List<User> users;

    @BeforeMethod
    public void initUsers() {
        user = new User(Long.valueOf("1"));
        user.setWeight(50);
        user.setEmail("email@gmail.com");
        user.setUserType(UserType.USER);
        user.setGender(Gender.FEMALE);
        user.setName("Lucy");
        user.setSurname("Strewn");
        user.setPasswordHash("password");
        user.setBirthdate(LocalDate.of(1999, 10, 15));


        admin = new User(Long.valueOf(2));
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

        users = Arrays.asList(user, admin);

    }


    @BeforeMethod(dependsOnMethods = "initUsers")
    public void initMocksBehaviour() {
        MockitoAnnotations.initMocks(this);

        when(userDAO.getAll()).thenReturn(users);

        when(userDAO.getByEmail(user.getEmail())).thenReturn(user);
        when(userDAO.getByEmail(admin.getEmail())).thenReturn(admin);
        when(userDAO.getByEmail("noemail@noemail.com")).thenReturn(null);

        when(userDAO.getById(user.getId())).thenReturn(user);
        when(userDAO.getById(admin.getId())).thenReturn(admin);
        when(userDAO.getById(20L)).thenReturn(null);

    }

    @Test
    public void registerTest() {
        userService.register(user, user.getPasswordHash());
        verify(userDAO).create(user);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void registerNullTest() {
        userService.register(null, "password");
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void registerNullPasswordTest() {
        userService.register(noid, null);
    }

    @Test
    public void registerEmptyTest() {
        userService.register(new User(), "paspaspaspaspaspas");
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateUserNullTest() {
        userService.update(null);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTest() {
        userService.delete(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNotExistingTest() {
        userService.delete(noid);
    }

    @Test
    public void isAdministratorAdministratorTest() {
        Assert.assertTrue(userService.isAdministrator(admin.getId()));
    }

    @Test
    public void isUserAdministratorTest() {
        Assert.assertFalse(userService.isAdministrator(user.getId()));
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void isAdministratorNullTest() {
        userService.isAdministrator(null);
    }

    @Test
    public void updateTest() {
        userService.update(user);
        verify(userDAO).update(userCaptor.capture());
        assertEquals(userCaptor.getValue(), user);
        assertEquals(userCaptor.getValue().getId(), user.getId());
    }

    @Test
    public void updateAdminBaseTest()  {
        assertNotNull(admin.getId());
        userService.update(admin);
        verify(userDAO).update(admin);
    }

    @Test
    public void updateTest2() {
        assertNotNull(user.getId());
        userService.update(user);
        verify(userDAO).update(user);
    }

    @Test
    public void updateEmailTest() {
        assertNotNull(user.getId());
        user.setEmail("changed@email.com");
        userService.update(user);
        verify(userDAO).update(userCaptor.capture());
        assertEquals(userCaptor.getValue(), user);
        assertEquals(userCaptor.getValue().getId(), user.getId());
    }

    @Test
    public void getAllTest() {
        List<User> res = userService.getAll();
        assertEquals(res.size(), users.size());
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            User r = res.get(i);
            assertEquals(r.getId(), u.getId());
            assertEquals(r, u);
        }
    }

    @Test
    public void getAllEmptyTest() {
        when(userDAO.getAll()).thenReturn(new ArrayList<>());
        assertEquals(userDAO.getAll().size(), 0);
    }

    @Test
    public void getByIdTest() {
        User r = userService.getById(user.getId());
        assertEquals(r.getId(), user.getId());
        assertEquals(r, user);
    }

    @Test
    public void getNotExistingTest() {
        assertNull(userService.getById(20L));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getByIdNullTest() {
        userService.getById(null);
    }

    @Test
    public void getByEmailTest() {
        User result = userService.getByEmail(user.getEmail());
        assertEquals(result.getId(), user.getId());
        assertEquals(result, user);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getByEmailNullTest() {
        userService.getByEmail(null);
    }

    @Test
    public void getNotExistingByEmailTest() {
        assertNull(userService.getByEmail("hi@hi.hi"));
    }

    @Test
    public void deleteUserTest() {
        userService.delete(user);
        verify(userDAO).delete(userCaptor.capture());
        assertEquals(userCaptor.getValue(), user);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    // the conditions changed, now, sole admin can not be deleted
    public void deleteAdminTest(){
        userService.delete(admin);
        verify(userDAO).delete(userCaptor.capture());
        assertEquals(userCaptor.getValue(), admin);

    }

    @Test
    public void authenticateTest() {
        userService.register(noid, "hellohowareyoutoday");
        Assert.assertTrue(userService.authenticate(noid, "hellohowareyoutoday"));
        Assert.assertFalse(userService.authenticate(noid, "meh"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void authenticateNullTest() {
        userService.register(null, "heyyyyyyyyyyyyyyya");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void authenticateNullPasswordTest() {
        userService.register(noid, null);
    }

}
