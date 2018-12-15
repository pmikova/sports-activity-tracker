package cz.muni.fi.PA165.tracker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.muni.fi.PA165.tracker.dto.UserCreateDTO;
import cz.muni.fi.PA165.tracker.dto.UserDTO;
import cz.muni.fi.PA165.tracker.exceptions.ConstraintViolationException;
import cz.muni.fi.PA165.tracker.exceptions.NotExistingEntityException;
import cz.muni.fi.PA165.tracker.exceptions.ResourceNotFoundException;
import cz.muni.fi.PA165.tracker.exceptions.ResourceNotModifiedException;
import cz.muni.fi.PA165.tracker.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Controller for User rest
 * @author pmikova 433345
 */
@RestController
@RequestMapping(ApiUris.USERS)
public class UserRestController {
    final static Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Inject
    private UserFacade userFacade;

    /**
     * Return all users
     * Command: curl -i -X GET http://localhost:8080/pa165/rest/users
     * @return list of UserDTO
     * @throws JsonProcessingException
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<UserDTO> getUsers() throws JsonProcessingException {

        logger.debug("rest getUsers()");
        return userFacade.getAll();
    }

    /**
     *
     * Get user by id
     * Command: curl -i -X GET http://localhost:8080/pa165/rest/users/{id}
     * @param id user id
     * @return UserDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUser(@PathVariable("id") long id) throws ResourceNotFoundException {

        logger.debug("rest getUser({})", id);
        UserDTO userDTO = userFacade.getById(id);
        if (userDTO == null){
            throw new ResourceNotFoundException();
        }
        return userDTO;


    }

    /**
     * Delete user.
     * Command: curl -i -X DELETE http://localhost:8080/pa165/rest/users/{id}
     * @param id of user to delete
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteUser(@PathVariable("id") long id) throws Exception {
        try {
            UserDTO dto = new UserDTO();
            dto.setId(id);
            userFacade.delete(dto);
        } catch (NotExistingEntityException | IllegalArgumentException e) {
            logger.error("Exception occurred while deleting the user:", e);
            throw new ResourceNotFoundException(e);
        } catch (DataAccessException e) {
            logger.error("Constraints violated:", e);
            throw new ConstraintViolationException(e);
        }
    }

    /**
     * Create user
     * Command: curl -X POST -i -H "Content-Type: application/json"
     * --data '{"attribute":"data"}' http://localhost:8080/pa165/rest/users
     * @param user user information to create
     * @return id of created user
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO createUser(@RequestBody UserCreateDTO user) throws Exception {
        try {
            Long id = userFacade.create(user);
            return userFacade.getById(id);
        } catch (NotExistingEntityException | IllegalArgumentException e) {
            logger.error("Exception occurred while creating the user:", e);
            throw new ResourceNotFoundException(e);
        } catch (DataAccessException e) {
            logger.error("Constraints violated:", e);
            throw new ConstraintViolationException(e);
        }
    }

    /**
     * This method updates user with given information.
     * Command: curl -i -X PUT -H "Content-Type: application/json"
     * --data '{"attribute":"data"}' http://localhost:8080/pa165/rest/users/{id}
     * @param id id of user
     * @param newUser UserDTO for updating user
     * @return user that was updated
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO updateUser(@PathVariable("id") long id, @RequestBody UserDTO newUser) throws Exception {
        UserDTO original = null;
        try {
            original = userFacade.getById(id);
        } catch (Exception e) {
            logger.error("Could not find user:", e);
            throw new ResourceNotFoundException(e);
        }

        UserDTO updateUser = compareUsers(original, newUser);
        try {
            userFacade.update(updateUser);
        } catch (Exception e) {
            logger.error("Could not update user:", e);
            throw new ResourceNotModifiedException(e);
        }

        return updateUser;
    }

    /**
     * This method creates a UserDTO from data entered in the commandline and from existing user in database.
     * @param original user stored in database
     * @param newUser user update information
     * @return
     */
    private UserDTO compareUsers(UserDTO original, UserDTO newUser) {
        UserDTO user = new UserDTO();
        user.setId(original.getId());
        user.setPasswordHash((newUser.getPasswordHash() == null) ? original.getPasswordHash()
                : newUser.getPasswordHash());
        user.setEmail((newUser.getEmail() == null) ? original.getEmail() : newUser.getEmail());
        user.setName((newUser.getName() == null) ? original.getName() : newUser.getName());
        user.setSurname((newUser.getSurname() == null) ? original.getSurname() : newUser.getSurname());
        user.setBirthdate((newUser.getBirthdate() == null) ? original.getBirthdate() : newUser.getBirthdate());
        user.setUserType((newUser.getUserType() == null) ? original.getUserType() : newUser.getUserType());
        user.setGender((newUser.getGender() == null) ? original.getGender() : newUser.getGender());
        user.setWeight((newUser.getWeight() < 1) ? original.getWeight() : newUser.getWeight());
        return user;
    }


}
