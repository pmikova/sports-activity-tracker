package cz.muni.fi.PA165.tracker.mvc.controllers;


import cz.muni.fi.PA165.tracker.dto.*;
import cz.muni.fi.PA165.tracker.facade.ActivityRecordFacade;
import cz.muni.fi.PA165.tracker.facade.BurnedCaloriesFacade;
import cz.muni.fi.PA165.tracker.facade.SportActivityFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/activityrecord")
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
        model.addAttribute("records", reports);
        return "activityrecord/index";
    }

//
//    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
//    public String detail(@PathVariable long id, Model model) {
//        BurnedCaloriesDTO calorieDTO = burnedCaloriesFacade.getById(id);
//        model.addAttribute("calorie", calorieDTO);
//        return "activityrecord/detail";
//    }
//

    @RequestMapping(value = {"/edit/{id}", "/edit/{id}/"}, method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        //BurnedCaloriesDTO caloriesDTO = burnedCaloriesFacade.getById(id);
        //ActivityRecordDTO recordDTO = caloriesDTO.getActivityRecord();
        ActivityRecordDTO recordDTO = activityRecordFacade.getById(id);
        List<SportActivityDTO> activityDTOS = sportActivityFacade.getAll();
        //model.addAttribute("id", id);
        ActivityRecordUpdateDTO updateDTO = new ActivityRecordUpdateDTO();
        updateDTO.setId(recordDTO.getId());
        updateDTO.setEndTime(recordDTO.getEndTime());
        updateDTO.setStartTime(recordDTO.getStartTime());
        updateDTO.setSportActivityId(recordDTO.getSportActivity().getId());

        model.addAttribute("activities", activityDTOS);
        model.addAttribute("record", updateDTO);
        if(getLoggedUser().equals(recordDTO.getUser())){
            return "activityrecord/edit";
        }else{
            redirectAttributes.addFlashAttribute("alert_danger", "You are not allowed to edit activities of other users.");
            return "redirect:activityrecord/index";
        }

    }

    @RequestMapping(value = {"/edit", "/edit/"}, method = RequestMethod.POST)
    public String edit(
            @Valid @ModelAttribute("record") ActivityRecordUpdateDTO formData,
            BindingResult bindingResult,
            UriComponentsBuilder uriBuilder,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            //? why would we do this?
            //model.addAttribute("activities", activityRecordFacade.getAll());
            return "activityrecord/edit";
        }

        model.addAttribute("activities", activityRecordFacade.getAll());
        try{
            activityRecordFacade.update(formData);
            redirectAttributes.addFlashAttribute("alert_success", "Activity Record with id " + formData.getId() + " has been updated");
        } catch (DataAccessException e) {
            log.error("Could not create sport activity" + e.getMessage(), e);
            redirectAttributes.addFlashAttribute("alert_danger", "Activity Record with id " + formData.getId() + " could not be updated.");
        }
        //return "redirect:/index";
        return "redirect:" + uriBuilder.path("/activityrecord/index").toUriString();
    }


    @RequestMapping(value ={"/create/", "/create"}, method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("activities", sportActivityFacade.getAll());
        model.addAttribute("recordCreate", new ActivityRecordCreateDTO());
        return "activityrecord/create";
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("recordCreate") ActivityRecordCreateDTO formData,
            BindingResult bindingResult,
            //@RequestParam(value = "distance", required=false) int distance,
            UriComponentsBuilder uriBuilder,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            model.addAttribute("activities", activityRecordFacade.getAll());
            return "activityrecord/create";
        }
        try{
            activityRecordFacade.create(formData);
            redirectAttributes.addFlashAttribute("alert_success", "Activity Record was created");
        } catch (DataAccessException e) {
            log.error("Could not create activity record" + e.getMessage(), e);
            redirectAttributes.addFlashAttribute("alert_danger", "Activity record can not be created.");
        }
        return "redirect:" + uriBuilder.path("/activityrecord/index").toUriString();
    }

    @RequestMapping(value = {"/delete/{id}", "/delete/{id}/"}, method = RequestMethod.POST)
    public String delete(
            @PathVariable long id,
            UriComponentsBuilder uriBuilder,
            RedirectAttributes redirectAttributes) {
        try {
            activityRecordFacade.delete(activityRecordFacade.getById(id));
            redirectAttributes.addFlashAttribute("alert_success", "ActivityRecord was deleted.");
        } catch (Exception ex) {
            log.error("Activityrecord " + id + " cannot be deleted");
            redirectAttributes.addFlashAttribute("alert_danger", "Activity record cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/activityrecord/index").toUriString();
    }
}
