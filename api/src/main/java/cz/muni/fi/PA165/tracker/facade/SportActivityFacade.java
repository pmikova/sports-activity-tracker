package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.dto.SportActivityCreateDTO;
import cz.muni.fi.PA165.tracker.dto.SportActivityDTO;
import cz.muni.fi.PA165.tracker.dto.SportActivityUpdateDTO;

import java.util.List;

/**
 * Facade interface for Sport Activity.
 * @author pmikova 433345
 */
public interface SportActivityFacade {

    /**
     * Creates SportActivity
     * @param activity activity to create
     * @return id of SportActivity object
     */
    void create(SportActivityCreateDTO activity);

    /**
     * Updates SportActivity.
     * @param activity activity to update
     */
    void update(SportActivityDTO activity);

    /**
     * Deletes SportActivity
     * @param activity activity to delete
     */
    void delete(SportActivityDTO activity);

    /**
     * Get SportActivity with given ID.
     * @param id id of activity we want to find
     * @return sportActivity with given id
     */
    SportActivityDTO getById(Long id);

    /**
     * List all SportActivities
     * @return list of all activities.
     */
    List<SportActivityDTO> getAll();
}
