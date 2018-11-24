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
 */

@Repository
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
                    .createQuery("SELECT u FROM User u WHERE u.email=:email",
                            User.class).setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u",
                User.class);
        return query.getResultList();
    }

    @Override
    public User update(User user) {
        if (user == null){
            throw new IllegalArgumentException("User record can not be null!");
        }
        return entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        if (user == null){
            throw new IllegalArgumentException("User record can not be null!");
        }
        entityManager.find(User.class, user.getId());
        entityManager.remove(user);
    }
}
