package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.dao.ActivityRecordDAO;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.User;

import javax.inject.Inject;
import java.util.List;

public class ActivityRecordServiceImpl implements ActivityRecordService {

    @Inject
    private ActivityRecordDAO activityRecordDAO;


    @Override
    public Long create(ActivityRecord activityRecord) {
        activityRecordDAO.create(activityRecord);
        return activityRecord.getId();
    }

    @Override
    public void update(ActivityRecord activityRecord) {
        activityRecordDAO.update(activityRecord);
    }

    @Override
    public void delete(ActivityRecord activityRecord) {
        activityRecord record = activityRecordDAO.
        activityRecordDAO.delete(activityRecord);
    }

    @Override
    public ActivityRecord getById(Long id) {
        return activityRecordDAO.getById(id);
    }

    @Override
    public List<ActivityRecord> getAll() {
        return activityRecordDAO.getAll();
    }


    //TODO:is this reasonable, or should it be in user?
    @Override
    public List<ActivityRecord> getAllByUser(Long id) {
        return null;
    }
}
