package cz.muni.fi.PA165.tracker.controllers;

import cz.muni.fi.PA165.tracker.dto.UserDTO;
import cz.muni.fi.PA165.tracker.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping(value = {"/user"})
public class UserController extends MainController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = {"settings", "settings/"}, method = RequestMethod.GET)
    public String settings(Model model) {
        UserDTO user = getUserDTO();
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

        UserDTO notUpdated = getUserDTO();
        formData.setId(notUpdated.getId());

        log.debug("update user({})", formData);

        if (bindingResult.hasErrors()) {
            return "user/settings";
        }

        userFacade.update(formData);
        redirectAttributes.addFlashAttribute("alert_success", "User " + formData.getEmail() + " was updated");

        return "redirect:/";
    }

    @RequestMapping(value = {"users", "users/"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", userFacade.getAll());
        return "user/users";
    }


}