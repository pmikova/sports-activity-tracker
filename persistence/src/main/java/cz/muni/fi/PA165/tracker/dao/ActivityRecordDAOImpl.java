package cz.muni.fi.PA165.tracker.dao;

import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

// this marks class as DAO
@Repository
// define as a transaction
@Transactional
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
        return entityManager.createQuery("SELECT a FROM activityrecord a", ActivityRecord.class).getResultList(); }


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
        entityManager.find(ActivityRecord.class, activityRecord);
        entityManager.remove(activityRecord);
    }

}
