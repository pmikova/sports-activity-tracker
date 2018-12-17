package cz.muni.fi.PA165.tracker.mvc.controllers;

import cz.muni.fi.PA165.tracker.dto.UserDTO;
import cz.muni.fi.PA165.tracker.facade.UserFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * Base controller that should be extended by other controllers
 * @author pmikova 433345
 */
public class MainController {

    @Inject
    private UserFacade userFacade;

    /**
     * Get currently logged usser in the application
     * @return logged users DTO
     */
    @ModelAttribute("loggedUser")
    protected UserDTO getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        String name = authentication.getName();

        if (name == null || name.equals("anonymousUser")) {
            return null;
        }

        UserDTO user = userFacade.getByEmail(name);
        return user;
    }

    /**
     * This method determines whether an user is admin or not
     * @param request request to process
     * @return true if user is admin, false otherwise
     */
    @ModelAttribute("isUserAdmin")
    protected boolean isUserAdmin(HttpServletRequest request) {
        return request.isUserInRole("ROLE_ADMIN");
    }

    /**
     * Method to process validation errors
     */
    protected void addValidationErrors(BindingResult bindingResult, Model model) {
        for (FieldError fe : bindingResult.getFieldErrors()) {
            model.addAttribute(fe.getField() + "_error", true);
        }
    }


}
