package cz.muni.fi.PA165.tracker.mvc.controllers;


import cz.muni.fi.PA165.tracker.dto.*;
import cz.muni.fi.PA165.tracker.facade.ActivityRecordFacade;
import cz.muni.fi.PA165.tracker.facade.BurnedCaloriesFacade;
import cz.muni.fi.PA165.tracker.facade.SportActivityFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/records")
public class ActivityRecordController extends MainController{
    private final static Logger log = LoggerFactory.getLogger(ActivityRecordController.class);

    @Autowired
    private ActivityRecordFacade activityRecordFacade;

    @Autowired
    private BurnedCaloriesFacade burnedCaloriesFacade;

    @Autowired
    private SportActivityFacade sportActivityFacade;

    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String list(Model model) {
            UserDTO loggedInUser = getLoggedUser();
            List<ActivityRecordDTO> reports = activityRecordFacade.getByUser(loggedInUser);
            model.addAttribute("reports", reports);
            return "activityrecord/index";
        }


    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {
        BurnedCaloriesDTO calorieDTO = burnedCaloriesFacade.getById(id);
        model.addAttribute("calorie", calorieDTO);
        return "activityrecord/detail";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        BurnedCaloriesDTO caloriesDTO = burnedCaloriesFacade.getById(id);
        ActivityRecordDTO recordDTO = caloriesDTO.getActivityRecord();
        List<SportActivityDTO> activityDTOS = sportActivityFacade.getAll();
        model.addAttribute("id", id);
        model.addAttribute("activities", activityDTOS);
        model.addAttribute("record", recordDTO);
        if(getLoggedUser().equals(caloriesDTO.getUser())){
            log.debug("");
            return "activityrecord/edit";
        }else{
            //TODO: some sort of a warning
            return "activityrecord/detail";
        }

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(
            @Valid @ModelAttribute("record") ActivityRecordDTO formData,
            UriComponentsBuilder uriBuilder,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        UserDTO notUpdated = getLoggedUser();
        formData.setId(notUpdated.getId());

        log.debug("Updating activity record");

        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "/detail/{id}";
        }
        activityRecordFacade.update(formData);
        return "redirect:/detail/{id}";
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(
            Model model,
            RedirectAttributes redirectAttributes,
            ActivityRecordCreateDTO formData
    ) {
        model.addAttribute("activities", sportActivityFacade.getAll());
        log.debug("");
        return "activityrecord/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("record") ActivityRecordCreateDTO formData,
            UriComponentsBuilder uriBuilder,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        UserDTO notUpdated = getLoggedUser();
        log.debug("creating activity record");

        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "/detail/{id}";
        }
        activityRecordFacade.create(formData);
        return "redirect:/detail/{id}";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(
            @PathVariable long id,
            UriComponentsBuilder uriBuilder,
            RedirectAttributes redirectAttributes) {
        ActivityRecordDTO record = activityRecordFacade.getById(id);
        log.debug("delete({})", id);
        try {
            activityRecordFacade.delete(record);
            redirectAttributes.addFlashAttribute("alert_success", "ActivityRecord \"" + record.getSportActivity().getActivityName()
                    + "\" performed by" + record.getUser().getName() + " on " + record.getStartTime().toString() + " was deleted.");
        } catch (Exception ex) {
            log.error("Activityrecord " + id + " cannot be deleted");
            redirectAttributes.addFlashAttribute("alert_danger", "Activity record \"" + record.getSportActivity().getActivityName() + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/activityrecords/list").toUriString();
    }


}
