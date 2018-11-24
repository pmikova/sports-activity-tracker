package cz.muni.fi.PA165.tracker.facade;


import cz.muni.fi.PA165.tracker.dto.UserAuthenticationDTO;
import cz.muni.fi.PA165.tracker.dto.UserCreateDTO;
import cz.muni.fi.PA165.tracker.dto.UserDTO;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.exceptions.NotExistingEntityException;
import cz.muni.fi.PA165.tracker.mapping.MappingService;
import cz.muni.fi.PA165.tracker.service.UserService;
import cz.muni.fi.PA165.tracker.service.UserStatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Long create(UserCreateDTO user) {
        if (user == null){
            throw new IllegalArgumentException("UserDTO can not be null!");
        }
        userService.register(mappingService.mapTo(user, User.class), user.getPasswordHash());
        return userService.getByEmail(user.getEmail()).getId();

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
}
