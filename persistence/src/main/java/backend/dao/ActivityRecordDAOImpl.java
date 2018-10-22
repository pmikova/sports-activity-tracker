package backend.dao;

import backend.entities.ActivityRecord;
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

    //TODO write query for find all
    public List<ActivityRecord> getAll(){
        return null;}


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
        entityManager.remove(entityManager.merge(activityRecord));
    }

}
