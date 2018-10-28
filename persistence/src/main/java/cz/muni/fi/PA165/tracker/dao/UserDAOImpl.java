package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of UserDAO interface.
 * @author Dominik-Bujna
 *  */

@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user){
        if(user == null){
              throw new IllegalArgumentException("User cannot be null");
        }
        entityManager.persist(user);
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException("Cannot search for null e-mail");

        try {
            return entityManager
                    .createQuery("select u from Users u where email=:email",
                            User.class).setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM users u",
                User.class);
        return query.getResultList();
    }

    //TODO implement the update method

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {
        entityManager.remove(getById(user.getId()));
    }
}
