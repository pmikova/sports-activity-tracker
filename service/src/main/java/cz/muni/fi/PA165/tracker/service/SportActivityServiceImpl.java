package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.dao.ActivityRecordDAO;
import cz.muni.fi.PA165.tracker.dao.SportActivityDAO;
import cz.muni.fi.PA165.tracker.entities.SportActivity;

import javax.inject.Inject;
import java.util.List;

/**
 * Service implementation for Sport Activity.
 * @author Dominik-Bujna
 */

public class SportActivityServiceImpl implements SportActivityService {

    @Inject
    private SportActivityDAO sportActivityDAO;

    @Override
    public void create(SportActivity sportActivity) {
        sportActivityDAO.create(sportActivity);
    }

    @Override
    public void update(SportActivity sportActivity) {
        sportActivityDAO.update(sportActivity);
    }

    @Override
    public void delete(SportActivity sportActivity) {
        sportActivityDAO.delete(sportActivity);
    }

    @Override
    public SportActivity getById(Long id) {
        return sportActivityDAO.getById(id);
    }

    @Override
    public List<SportActivity> getAll() {
        return sportActivityDAO.getAll();
    }
}
