package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

// this marks class as DAO
@Repository
/**
 * Implementation of ActivityRecordDAO interface.
 * @author pmikova 433345
 */
public class ActivityRecordDAOImpl implements ActivityRecordDAO{

    @PersistenceContext
    private EntityManager entityManager;

    public void create(ActivityRecord activityRecord){

        if (activityRecord == null){
            throw new IllegalArgumentException("Activity record can not be null!");
        }
        entityManager.persist(activityRecord);
    }

    public ActivityRecord getById(Long id){
        return entityManager.find(ActivityRecord.class, id);

    }

    public List<ActivityRecord> getAll(){
        return entityManager.createQuery("SELECT a FROM ActivityRecord a", ActivityRecord.class).getResultList(); }


    public void update(ActivityRecord activityRecord){

        if (activityRecord == null){
            throw new IllegalArgumentException("Activity record can not be null!");
        }
        entityManager.merge(activityRecord);

    }

    public void delete(ActivityRecord activityRecord){
        if (activityRecord == null){
            throw new IllegalArgumentException("Activity record can not be null!");
        }
        // ensure the entity is associated with persistence context before removing it
        entityManager.find(ActivityRecord.class, activityRecord.getId());
        entityManager.remove(activityRecord);
    }

    @Override
    public List<ActivityRecord> getByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is not valid");
        }
        try {
            TypedQuery<ActivityRecord> q = entityManager.createQuery(
                    "SELECT a FROM ActivityReport a WHERE a.user = :user",
                    ActivityRecord.class).setParameter("user", user);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ActivityRecord> getByActivity(SportActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity is not valid");
        }
        try {
            TypedQuery<ActivityRecord> q = entityManager.createQuery(
                    "SELECT a FROM ActivityReport a WHERE a.sportActivity = :sportActivity",
                    ActivityRecord.class).setParameter("sportActivity", activity);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void deleteByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is not valid");
        }
        entityManager.createQuery("DELETE FROM ActivityReport a WHERE a.user.id = :user")
                .setParameter("user", user.getId())
                .executeUpdate();
    }

}
