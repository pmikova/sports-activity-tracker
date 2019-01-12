package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.dao.ActivityRecordDAO;
import cz.muni.fi.PA165.tracker.dao.SportActivityDAO;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.ActivityRecord;
import cz.muni.fi.PA165.tracker.entities.BurnedCalories;
import cz.muni.fi.PA165.tracker.service.BurnedCaloriesService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service implementation for Sport Activity.
 * @author Dominik-Bujna
 */

@Service
public class SportActivityServiceImpl implements SportActivityService {

    @Inject
    private SportActivityDAO sportActivityDAO;

    @Inject
    private ActivityRecordDAO activityRecordDAO;

    @Inject
    private BurnedCaloriesService bcs;

    @Override
    public void create(SportActivity sportActivity) {
        if(sportActivity == null) throw new IllegalArgumentException("SportActivity cannot be null.");
        sportActivityDAO.create(sportActivity);
    }

    @Override
    public void update(SportActivity sportActivity) {
        if(sportActivity == null) throw new IllegalArgumentException("SportActivity cannot be null.");
        sportActivityDAO.update(sportActivity);

        //update burnedCalories objects with this activity
        List<ActivityRecord> recordList = activityRecordDAO.getByActivity(sportActivity);
        for (ActivityRecord a : recordList) {
            BurnedCalories bc = bcs.getByActivityId(a.getId());
            bcs.computeBurnedCalories(bc);
            bcs.update(bc);
        }

    }

    @Override
    public void delete(SportActivity sportActivity) {
        if(sportActivity == null) throw new IllegalArgumentException("SportActivity cannot be null.");
        sportActivityDAO.delete(sportActivity);
    }

    @Override
    public SportActivity getById(Long id) {
        if(id == null) throw new IllegalArgumentException("Id cannot be null.");
        return sportActivityDAO.getById(id);
    }

    @Override
    public List<SportActivity> getAll() {
        return sportActivityDAO.getAll();
    }
}
