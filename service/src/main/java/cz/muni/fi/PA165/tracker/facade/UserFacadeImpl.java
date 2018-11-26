package cz.muni.fi.PA165.tracker.facade;


import cz.muni.fi.PA165.tracker.dto.*;
import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.exceptions.NotExistingEntityException;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import cz.muni.fi.PA165.tracker.service.UserService;
import cz.muni.fi.PA165.tracker.service.UserStatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User Facade implementation.
 * @author TODO
 */

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Inject
    private UserService userService;

    @Inject
    private MappingService mappingService;

    @Inject
    private UserStatService userStatService;

    @Override
    public Long create(UserCreateDTO user) {
        if (user == null){
            throw new IllegalArgumentException("UserDTO can not be null!");
        }
        userService.register(mappingService.mapTo(user, User.class), user.getPasswordHash());
        User u = userService.getByEmail(user.getEmail());
        return u.getId();

    }

    @Override
    public void update(UserDTO user) {
        if (user == null){
            throw new IllegalArgumentException("UserDTO can not be null!");
        }

        User userMap = mappingService.mapTo(user, User.class);
        if (userService.getById(userMap.getId()) == null) {
            throw new NotExistingEntityException("User must exist!");
        }

        userService.update(userMap);
    }

    @Override
    public void delete(UserDTO user) {
        if (user == null){
            throw new IllegalArgumentException("UserDTO can not be null!");
        }
        userService.delete(mappingService.mapTo(user, User.class));
    }

    @Override
    public UserDTO getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cant be null!");
        }
        User user = userService.getById(id);
        if (user == null) {
            throw new NotExistingEntityException("No user with id: " + id);
        }
        UserDTO userDTO = mappingService.mapTo(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> resultEntities = userService.getAll();
        List<UserDTO> all = new ArrayList<>(resultEntities.size());
        for (User user : resultEntities) {
            UserDTO userDTO = mappingService.mapTo(user, UserDTO.class);
            all.add(userDTO);
        }
        return all;
    }

    @Override
    public UserDTO getByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email is null");
        }
        User user = userService.getByEmail(email);
        if (user == null) {
            throw new NotExistingEntityException("No user with email: " + email);
        }
        UserDTO userDTO = mappingService.mapTo(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public boolean logIn(UserAuthenticationDTO auth) {
        if (auth == null){
            throw new NotExistingEntityException("UserAuthenticationDTO can not be null!");
        }
        User user = userService.getById(auth.getId());
        return userService.authenticate(user, user.getPasswordHash());
    }

    @Override
    public boolean isAdministrator(UserDTO user) {
        if (user == null){
            throw new IllegalArgumentException("User can not be null!");
        }
        User userEntity = mappingService.mapTo(user, User.class);
        return userService.isAdministrator(userEntity);
    }

    @Override
    public UserStatDTO getStats(UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO is null");
        }
        User user = userService.getById(userDTO.getId());
        if (user == null) {
            throw new NotExistingEntityException("User does not exist");
        }
        UserStatDTO statisticsDTO = new UserStatDTO();
        statisticsDTO.setUserDTO(mappingService.mapTo(user, UserDTO.class));

        statisticsDTO.setCalories(userStatService.getAllCalories(user));
        statisticsDTO.setCaloriesWeek(
                userStatService.getAllCalories(user, LocalDate.now().minusWeeks(1), LocalDate.now())
        );
        statisticsDTO.setCaloriesMonth(
                userStatService.getAllCalories(user, LocalDate.now().minusMonths(1), LocalDate.now())
        );

        statisticsDTO.setActivities(userStatService.getNumberOfActivities(user));
        statisticsDTO.setActivitiesWeek(
                userStatService.getNumberOfActivities(user, LocalDate.now(), LocalDate.now().minusWeeks(1))
        );
        statisticsDTO.setActivitiesMonth(
                userStatService.getNumberOfActivities(user, LocalDate.now(), LocalDate.now().minusMonths(1))
        );

        Map<SportActivity, Integer> sportsAndCount = userStatService.countActivitiesForUser(user);
        statisticsDTO.setActivitiesSumUp(
                mappingService.mapTo(sportsAndCount, SportActivityDTO.class)
        );

        return statisticsDTO;}

}
