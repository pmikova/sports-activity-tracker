package cz.muni.fi.PA165.tracker.mvc.controllers;

import cz.muni.fi.PA165.tracker.dto.SportActivityCreateDTO;
import cz.muni.fi.PA165.tracker.dto.SportActivityDTO;
import cz.muni.fi.PA165.tracker.facade.SportActivityFacade;
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

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Controller for Sport Activities
 * @author HonzaOstrava 422539
 */
@Controller
@RequestMapping(value = {"/"})
public class SportActivityController extends MainController {

    private static final Logger log = LoggerFactory.getLogger(SportActivityController.class);

    @Inject
    private SportActivityFacade sportActivityFacade;

    @RequestMapping(value = {"activities", "/activities"}, method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("activities", sportActivityFacade.getAll());
        return "activities/activities";
    }

    @RequestMapping(value = "activities/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("activityCreate", new SportActivityCreateDTO());
        return "activities/create";
    }

    @RequestMapping(value = "activities/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("activityCreate") SportActivityCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "activities/create";
        }
        try {
            sportActivityFacade.create(formBean);
            redirectAttributes.addFlashAttribute("alert_success", "Sport Activity was created");
        } catch (DataAccessException e) {
            log.error("Could not create sport activity" + e.getMessage(), e);
            redirectAttributes.addFlashAttribute("alert_danger", "Sport Activity can not be created.");
        }
        return "redirect:" + uriBuilder.path("/activities").toUriString();
    }

    @RequestMapping(value = "activities/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        SportActivityDTO sportActivity = sportActivityFacade.getById(id);
        model.addAttribute("activityUpdate", sportActivity);
        return "activities/update";
    }

    @RequestMapping(value = "activities/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("activityUpdate") SportActivityDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "activities/update";
        }
        try {
            sportActivityFacade.update(formBean);
            redirectAttributes.addFlashAttribute("alert_success", "Sport Activity with id " + formBean.getId() + " was updated");
        } catch (DataAccessException e) {
            log.error("Could not create sport activity" + e.getMessage(), e);
            redirectAttributes.addFlashAttribute("alert_danger", "Sport Activity with id " + formBean.getId() + " can not be updated.");
        }
        return "redirect:" + uriBuilder.path("/activities").toUriString();
    }

    @RequestMapping(value = "activities/remove/{id}", method = RequestMethod.POST)
    public String remove(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        try {
            sportActivityFacade.delete(sportActivityFacade.getById(id));
            redirectAttributes.addFlashAttribute("alert_success", "Sport Activity with id " + id + " was deleted");
        } catch (DataAccessException e) {
            log.error("Could not delete sport activity" + e.getMessage(), e);
            redirectAttributes.addFlashAttribute("alert_danger", "Sport Activity with id " + id + " can not be deleted. It is used in Activity Record!");
        }
        return "redirect:" + uriBuilder.path("/activities").toUriString();
    }
}
