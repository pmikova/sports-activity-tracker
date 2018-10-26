package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.entities.SportActivity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

// this marks class as DAO
@Repository
// define as a transaction
@Transactional
/**
 * Implementation of SportActivityDAO interface.
 * @author HonzaOstrava
 *  */
public class SportActivityDAOImpl implements SportActivityDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(SportActivity activity) {
        if (activity == null){
            throw new IllegalArgumentException("Activity can not be null!");
        }
        entityManager.persist(activity);
    }

    @Override
    public SportActivity getById(Long id) {
        return entityManager.find(SportActivity.class, id);
    }

    @Override
    public List<SportActivity> getAll() {
        return entityManager.createQuery("SELECT a FROM SportActivity a", SportActivity.class).getResultList();
    }

    @Override
    public void update(SportActivity activity) {
        if (activity == null){
            throw new IllegalArgumentException("Activity can not be null!");
        }
        entityManager.merge(activity);
    }

    @Override
    public void delete(SportActivity activity) {
        if (activity == null){
            throw new IllegalArgumentException("Activity can not be null!");
        }
        // TODO change to use find when the SportActivity class have ID
        entityManager.remove(entityManager.merge(activity));
    }
}
