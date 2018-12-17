package cz.muni.fi.PA165.tracker.mvc.controllers;

import cz.muni.fi.PA165.tracker.facade.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * Home controller.
 * @author pmikova 433345
 */
@Controller
public class HomeController extends MainController {

    @Inject
    private UserFacade userFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        if (getLoggedUser() != null) {
            model.addAttribute("statistics", userFacade.getStats(getLoggedUser()));
        }
        return "index";
    }
}