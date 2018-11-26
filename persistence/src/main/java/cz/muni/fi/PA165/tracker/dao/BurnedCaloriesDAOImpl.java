package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.BurnedCalories;
import cz.muni.fi.PA165.tracker.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of BurnedCaloriesDAO interface.
 * @author Dominik-Bujna
 **/


@Repository
public class BurnedCaloriesDAOImpl implements BurnedCaloriesDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(BurnedCalories calories) {
        entityManager.persist(calories);
    }

    @Override
    public BurnedCalories getById(Long id) {
        return entityManager.find(BurnedCalories.class, id);
    }

    @Override
    public List<BurnedCalories> getAll() {
        TypedQuery<BurnedCalories> query = entityManager.createQuery("SELECT c FROM BurnedCalories c",
                BurnedCalories.class);
        return query.getResultList();
    }

    @Override
    public void update(BurnedCalories calories) {

        if (calories == null){
            throw new IllegalArgumentException("Calories record can not be null!");
        }
        entityManager.merge(calories);

    }

    @Override
    public void delete(BurnedCalories calories) {
        if (calories == null){
            throw new IllegalArgumentException("Calories record can not be null!");
        }
        entityManager.find(BurnedCalories.class, calories.getId());
        entityManager.remove(calories);
    }

    @Override
    public List<BurnedCalories> getByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can not be null");
        }
        try {
            TypedQuery<BurnedCalories> q = entityManager.createQuery(
                    "SELECT a FROM BurnedCalories a WHERE a.user = :user",
                    BurnedCalories.class).setParameter("user", user);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void deleteByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can not be null");
        }
        entityManager.createQuery("DELETE FROM BurnedCalories a WHERE a.user.id = :user")
                .setParameter("user", user.getId())
                .executeUpdate();
    }

    @Override
    public List<BurnedCalories> getByActivity(ActivityRecord activityRecord) {
        TypedQuery<BurnedCalories> query = entityManager.createQuery("SELECT c FROM BurnedCalories c WHERE c.activityRecord = :activityRecord",
                BurnedCalories.class);
        query.setParameter("activityRecord", activityRecord);
        return query.getResultList();
    }
}
