package cz.muni.fi.PA165.tracker.facade;

import cz.muni.fi.PA165.tracker.dto.SportActivityCreateDTO;
import cz.muni.fi.PA165.tracker.dto.SportActivityDTO;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import cz.muni.fi.PA165.tracker.service.SportActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Sport Activity Facade implementation.
 * @author Dominik-Bujna
 */

@Service
@Transactional

public class SportActivityFacadeImpl implements SportActivityFacade {

    @Inject
    private MappingService mappingService;

    @Inject
    private SportActivityService sportActivityService;

    @Override
    public void create(SportActivityCreateDTO sportActivityCreateDTO) {
        SportActivity sportActivity = mappingService.mapTo(sportActivityCreateDTO, SportActivity.class);
        sportActivityService.create(sportActivity);
    }

    @Override
    public void update(SportActivityDTO sportActivityDTO) {
        SportActivity sportActivity = mappingService.mapTo(sportActivityDTO, SportActivity.class);
        sportActivityService.update(sportActivity);
    }

    @Override
    public void delete(SportActivityDTO sportActivityDTO) {
        SportActivity sportActivity = mappingService.mapTo(sportActivityDTO, SportActivity.class);
        sportActivityService.delete(sportActivity);
    }

    @Override
    public SportActivityDTO getById(Long id) {
        return mappingService.mapTo(sportActivityService.getById(id), SportActivityDTO.class);
    }

    @Override
    public List<SportActivityDTO> getAll() {
        return mappingService.mapTo(sportActivityService.getAll(), SportActivityDTO.class);
    }
}
