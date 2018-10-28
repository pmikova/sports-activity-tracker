package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.entities.BurnedCalories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of BurnedCaloriesDAO interface.
 * @author Dominik-Bujna
 **/
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
        return (List<BurnedCalories>) query.getResultList();
    }

    //TODO:write update query
    @Override
    public void update(BurnedCalories calories) {

    }

    @Override
    public void delete(BurnedCalories calories) {
        entityManager.remove(calories);
    }
}
