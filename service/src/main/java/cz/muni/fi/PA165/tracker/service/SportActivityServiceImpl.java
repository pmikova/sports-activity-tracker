package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.dao.ActivityRecordDAO;
import cz.muni.fi.PA165.tracker.dao.SportActivityDAO;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
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

    @Override
    public void create(SportActivity sportActivity) {
        if(sportActivity == null) throw new IllegalArgumentException("SportActivity cannot be null.");
        sportActivityDAO.create(sportActivity);
    }

    @Override
    public void update(SportActivity sportActivity) {
        if(sportActivity == null) throw new IllegalArgumentException("SportActivity cannot be null.");
        sportActivityDAO.update(sportActivity);
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
