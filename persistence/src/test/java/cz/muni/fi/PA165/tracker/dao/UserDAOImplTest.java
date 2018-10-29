package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.PersistenceApplicationContext;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.enums.Gender;
import cz.muni.fi.PA165.tracker.enums.UserType;
import org.hibernate.PropertyValueException;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;


/**
 * Tests for UserDAOImpl class.
 * @author pmikova 4333454
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDAOImplTest extends AbstractTestNGSpringContextTests {

    @Inject
    private UserDAO userDAO;

    @PersistenceContext
    private EntityManager entityManager;

    private User user1;
    private User user2;
    private User user3;
    private User user4;

    @BeforeMethod
    public void setUp() {
        //user1 setup
        user1 = new User();
        user1.setEmail("johnd@email.com");
        user1.setGender(Gender.MALE);
        user1.setName("John");
        user1.setSurname("Doe");
        user1.setWeight(90);
        user1.setPasswordHash("password");
        LocalDate bdate = LocalDate.of(1950,10,13);
        user1.setBirthdate(bdate);
        user1.setUserType(UserType.USER);

        //user2 setup
        user2 = new User();
        user2.setEmail("lucy@email.com");
        user2.setGender(Gender.FEMALE);
        user2.setName("Lucy");
        user2.setSurname("Doe");
        user2.setWeight(55);
        user2.setPasswordHash("password2");
        LocalDate bdate2 = LocalDate.of(1991, 8, 16);
        user2.setBirthdate(bdate2);
        user2.setUserType(UserType.USER);

        //user3 setup
        user3 = new User();
        user3.setEmail("john@email.com");
        user3.setGender(Gender.MALE);
        user3.setName("John");
        user3.setSurname("Snow");
        user3.setWeight(110);
        user3.setPasswordHash("password3");
        LocalDate bdate3 = LocalDate.of(1980, 5, 21);
        user3.setBirthdate(bdate3);
        user3.setUserType(UserType.ADMIN);

        //user4 setup
        user4 = new User();
        user4.setEmail("kira@email.com");
        user4.setGender(Gender.FEMALE);
        user4.setName("Kira");
        user4.setSurname("Stewart");
        user4.setWeight(74);
        user4.setPasswordHash("password4");
        LocalDate bdate4 = LocalDate.of(1975, 11, 11);
        user4.setBirthdate(bdate4);
        user4.setUserType(UserType.USER);
    }
    @Test
    public void testCreate(){
        userDAO.create(user1);
        entityManager.flush();
        Assert.assertEquals(this.userDAO.getById(user1.getId()), user1);
    }
    @Test
    public void testGetById(){
        entityManager.persist(user1);
        entityManager.flush();
        User user = userDAO.getById(user1.getId());
        Assert.assertEquals(user1, user);

    }
    @Test
    public void testGetByEmail(){
        entityManager.persist(user2);
        entityManager.flush();
        User user = userDAO.getByEmail(user2.getEmail());
        Assert.assertEquals(user2, user);

    }
    @Test
    public void testGetAll(){
        Assert.assertEquals(userDAO.getAll().size(), 0);
        entityManager.persist(user3);
        entityManager.flush();
        Assert.assertEquals(userDAO.getAll().size(), 1);
        entityManager.persist(user4);
        entityManager.flush();
        Assert.assertEquals(userDAO.getAll().size(), 2);
        entityManager.persist(user1);
        entityManager.flush();
        Assert.assertEquals(userDAO.getAll().size(), 3);
    }
    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateNull(){
        userDAO.create(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testRemoveNotExistingUser(){
        User user = new User();
        userDAO.delete(user);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testRemoveNullUser(){
        userDAO.delete(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testGetByEmailNull(){
        userDAO.getByEmail(null);
    }


    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateNull(){
        entityManager.persist(user2);
        entityManager.flush();
        userDAO.update(null);

    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testUpdateEmailNull(){
        entityManager.persist(user1);
        entityManager.flush();
        user1.setEmail(null);
        userDAO.update(user1);
        entityManager.flush();

    }

    @Test
    public void testUpdate(){
        entityManager.persist(user2);
        entityManager.flush();
        user2.setName("Susan");
        user2.setWeight(50);
        user2.setPasswordHash("greatPassword");
        userDAO.update(user2);
        Assert.assertEquals(userDAO.getById(user2.getId()), user2);
    }

    @Test
    public void testUpdateOther(){
        entityManager.persist(user3);
        entityManager.flush();
        user3.setName("Rex");
        user3.setSurname("Forehead");
        user3.setEmail("hello@gmail.com");
        user3.setGender(Gender.FEMALE);
        user3.setWeight(72);
        user3.setPasswordHash("bestPassword");
        user3.setUserType(UserType.ADMIN);
        userDAO.update(user3);
        Assert.assertEquals(userDAO.getById(user3.getId()), user3);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void testChangeUserIdFails(){
        entityManager.persist(user3);
        entityManager.flush();
        Long id = user3.getId();
        Long idReplace = id * 2;
        user3.setId(idReplace);
        userDAO.update(user3);
        entityManager.flush();
    }

    @Test
    public void testDelete(){
        entityManager.persist(user3);
        entityManager.flush();
        userDAO.delete(user3);
        Assert.assertNull(userDAO.getById(user3.getId()));
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateIncompleteUser(){
        User incompleteUser = new User();
        incompleteUser.setWeight(10);
        incompleteUser.setName("Alice");
        incompleteUser.setBirthdate(LocalDate.of(1999,10,05));
        incompleteUser.setSurname("Strewn");
        incompleteUser.setGender(Gender.FEMALE);
        //dont fill email or password
        userDAO.create(incompleteUser);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testInvalidMail(){
        entityManager.persist(user3);
        entityManager.flush();
        user3.setEmail("emailemail");
        userDAO.update(user3);
        entityManager.flush();

    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createInvalidWeight(){
        user4.setWeight((long) -30);
        entityManager.persist(user4);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateIncompleteUser2(){
        User incompleteUser = new User();
        incompleteUser.setName("Alice");
        incompleteUser.setBirthdate(LocalDate.of(1999,10,05));
        incompleteUser.setSurname("Strewn");
        incompleteUser.setGender(Gender.FEMALE);
        incompleteUser.setEmail("email.gmail@gmail.com");
        incompleteUser.setPasswordHash("hellopassword");
        incompleteUser.setUserType(UserType.USER);
        userDAO.create(incompleteUser);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateUserZeroWeight(){
        User user = new User();
        user.setEmail("johnd@email.com");
        user.setGender(Gender.MALE);
        user.setName("John");
        user.setSurname("Doe");
        user.setWeight(0);
        user.setPasswordHash("password");
        LocalDate bdate = LocalDate.of(1950,10,13);
        user.setBirthdate(bdate);
        userDAO.create(user);
    }

}