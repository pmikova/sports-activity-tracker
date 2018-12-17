package cz.muni.fi.PA165.tracker.mvc.controllers;
import javax.inject.Inject;
import javax.validation.Valid;

import cz.muni.fi.PA165.tracker.dto.UserCreateDTO;
import cz.muni.fi.PA165.tracker.dto.UserDTO;
import cz.muni.fi.PA165.tracker.dto.UserStatDTO;
import cz.muni.fi.PA165.tracker.enums.UserType;
import cz.muni.fi.PA165.tracker.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Controller for user operations.
 */
@Controller
@RequestMapping(value = {"/"})
public class UserController extends MainController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserFacade userFacade;

    /**
     * Controller for user settings
     */
    @RequestMapping(value = {"settings", "settings/"}, method = RequestMethod.GET)
    public String settings(Model model) {
        UserDTO user = getLoggedUser();
        log.debug("update userUpdate({})", user);

        model.addAttribute("user", user);
        return "user/settings";
    }

    /**
     * Controller for user POST settings.
     */
    @RequestMapping(value = {"settings", "settings/"}, method = RequestMethod.POST)
    public String settings(
            @Valid @ModelAttribute("user") UserDTO formData,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        UserDTO notUpdated = getLoggedUser();
        formData.setId(notUpdated.getId());

        log.debug("Updating user...");

        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "user/settings";
        }

        userFacade.update(formData);
        redirectAttributes.addFlashAttribute("alert_success", "User was updated");

        return "redirect:/statistics";
    }

    /**
     * Registration controller
     */
    @RequestMapping(value = {"register", "register/"}, method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("newUser", new UserCreateDTO());
        return "user/register";
    }

    /**
     * Registration POST controller.
     */
    @RequestMapping(value = {"register", "register/"}, method = RequestMethod.POST)
    public String register(
            @Valid @ModelAttribute("newUser") UserCreateDTO formData,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        log.debug("Register user.");

        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "user/register";
        }

        userFacade.create(formData);
        redirectAttributes.addFlashAttribute("alert_success", "User was created");

        return "redirect:/login";
    }

    /**
     * Controller for Users table.
     */
    @RequestMapping(value = {"users", "users/"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", userFacade.getAll());
        return "user/users";
    }

    /**
     * Controller to make user administrator
     */
    @RequestMapping(value = {"/users/makeAdministrator/{id}"}, method = RequestMethod.GET)
    public String makeAdministrator(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder,
                            RedirectAttributes redirectAttributes) {
        log.info("Make user an administrator.");
        UserDTO updated = userFacade.getById(id);
        updated.setUserType(UserType.ADMIN);
        userFacade.update(updated);

        if (updated.getId().equals(getLoggedUser().getId())) {
            redirectAttributes.addFlashAttribute("alert_danger", "You changed your permissions. Please log in again.");
            return "redirect:" + uriBuilder.path("/logout").toUriString();
        }
        return "redirect:" + uriBuilder.path("/users").toUriString();
    }

    /**
     * Controller to make user a normal user.
     */
    @RequestMapping(value = {"/users/makeUser/{id}"}, method = RequestMethod.GET)
    public String makeUser(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder,
                              RedirectAttributes redirectAttributes) {
        log.info("Making user a regular user.");
        UserDTO updated = userFacade.getById(id);
        updated.setUserType(UserType.USER);
        userFacade.update(updated);

        if (updated.getId().equals(getLoggedUser().getId())) {
            redirectAttributes.addFlashAttribute("alert_danger", "Your role was updated. Please log in.");
            return "redirect:" + uriBuilder.path("/logout").toUriString();
        }
        return "redirect:" + uriBuilder.path("/users").toUriString();
    }

    @RequestMapping(value = "users/remove/{id}", method = RequestMethod.POST)
    public String remove(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {

        if (id == getLoggedUser().getId()) {
            log.error("Deleting itself is not allowed!");
            redirectAttributes.addFlashAttribute("alert_danger", "You can't delete yourself!");
            return "redirect:" + uriBuilder.path("/users").toUriString();
        }

        try {
            userFacade.delete(userFacade.getById(id));
            redirectAttributes.addFlashAttribute("alert_success", "User with id " + id + " was deleted");
        } catch (DataAccessException e) {
            log.error("Could not delete user" + e.getMessage(), e);
            redirectAttributes.addFlashAttribute("alert_danger", "User with id " + id + " can not be deleted.");
        }
        return "redirect:" + uriBuilder.path("/users").toUriString();
    }

    @RequestMapping(value = "statistics", method = RequestMethod.GET)
    public String statistics(Model model) {
        UserDTO loggedUser = getLoggedUser();
        UserStatDTO userStats = userFacade.getStats(loggedUser);
        model.addAttribute("userStats", userStats);
        return "user/statistics";
    }
}