package cz.muni.fi.PA165.tracker.mvc.controllers;
import javax.inject.Inject;
import javax.validation.Valid;

import cz.muni.fi.PA165.tracker.dto.UserCreateDTO;
import cz.muni.fi.PA165.tracker.dto.UserDTO;
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
import org.springframework.web.util.UriComponentsBuilder;;


@Controller
@RequestMapping(value = {"/"})
public class UserController extends MainController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserFacade userFacade;

    @RequestMapping(value = {"settings", "settings/"}, method = RequestMethod.GET)
    public String settings(Model model) {
        UserDTO user = getLoggedUser();
        log.debug("update userUpdate({})", user);

        model.addAttribute("user", user);
        return "user/settings";
    }

    @RequestMapping(value = {"settings", "settings/"}, method = RequestMethod.POST)
    public String settings(
            @Valid @ModelAttribute("user") UserDTO formData,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        UserDTO notUpdated = getLoggedUser();
        formData.setId(notUpdated.getId());

        log.debug("update user({})", formData);

        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "user/settings";
        }

        userFacade.update(formData);
        redirectAttributes.addFlashAttribute("alert_success", "User " + formData.getEmail() + " was updated");

        return "redirect:/";
    }

    @RequestMapping(value = {"register", "register/"}, method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("newUser", new UserCreateDTO());
        return "user/register";
    }

    @RequestMapping(value = {"register", "register/"}, method = RequestMethod.POST)
    public String register(
            @Valid @ModelAttribute("newUser") UserCreateDTO formData,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        log.debug("register user({})", formData);

        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "user/register";
        }

        userFacade.create(formData);
        redirectAttributes.addFlashAttribute("alert_success", "User " + formData.getEmail() + " was created");

        return "redirect:/login";
    }

    @RequestMapping(value = {"users", "users/"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", userFacade.getAll());
        return "user/users";
    }

}