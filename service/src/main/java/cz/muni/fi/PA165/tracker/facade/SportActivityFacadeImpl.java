package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.dto.SportActivityCreateDTO;
import cz.muni.fi.PA165.tracker.dto.SportActivityDTO;
import cz.muni.fi.PA165.tracker.dto.SportActivityUpdateDTO;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import cz.muni.fi.PA165.tracker.service.ActivityRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Sport Activity Facade implementation.
 * @author TODO
 */

@Service
@Transactional

public class SportActivityFacadeImpl implements SportActivityFacade {

    @Inject
    private MappingService mappingService;

    @Inject
    private ActivityRecordService activityRecordService;

    @Override
    public Long create(SportActivityCreateDTO activity) {
        return (long) 0.0;
    }

    @Override
    public void update(SportActivityUpdateDTO activity) {

    }

    @Override
    public void delete(SportActivityDTO activity) {

    }

    @Override
    public SportActivityDTO getById(Long id) {
        return null;
    }

    @Override
    public List<SportActivityDTO> getAll() {
        return null;
    }
}
