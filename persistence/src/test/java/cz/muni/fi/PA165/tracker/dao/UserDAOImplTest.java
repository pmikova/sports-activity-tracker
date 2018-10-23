package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.entities.User;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Tests for UserDAOImpl class.
 * @author pmikova 4333454
 * TODO: what testing framework to use? junit? testng? does it matter?
 */
public class UserDAOImplTest {

    private User user1;
    private User user2;
    private User user3;
    private User user4;

    public void setUp() {
        //user1 setup
        user1 = new User();
        user1.setEmail("johnd@email.com");
        user1.setGender(User.Gender.MALE);
        user1.setName("John");
        user1.setSurname("Doe");
        user1.setWeight(90);
        Calendar bdate = Calendar.getInstance();
        bdate.set(1950, 10, 13);
        user1.setBirthdate(bdate);

        //user2 setup
        user2 = new User();
        user2.setEmail("lucy@email.com");
        user2.setGender(User.Gender.FEMALE);
        user2.setName("Lucy");
        user2.setSurname("Doe");
        user2.setWeight(55);
        Calendar bdate2 = Calendar.getInstance();
        bdate2.set(1991, 8, 16);
        user2.setBirthdate(bdate);

        //user3 setup
        user3 = new User();
        user3.setEmail("john@email.com");
        user3.setGender(User.Gender.MALE);
        user3.setName("John");
        user3.setSurname("Snow");
        user3.setWeight(110);
        Calendar bdate3 = Calendar.getInstance();
        bdate3.set(1980, 5, 21);
        user3.setBirthdate(bdate);

        //user4 setup
        user4 = new User();
        user4.setEmail("kira@email.com");
        user4.setGender(User.Gender.FEMALE);
        user4.setName("Kira");
        user4.setSurname("Stewart");
        user4.setWeight(74);
        Calendar bdate4 = Calendar.getInstance();
        bdate4.set(1975, 11, 11);
        user4.setBirthdate(bdate);


    }


    public void testCreate(User user){}

    public User testGetById(Long id){return null;}

    public User testGetByEmail(String email){return null;}

    public List<User> testGetAll(){return null;}

    public void testUpdate(User user){}

    public void testDelete(User user){}



}
