package cz.muni.fi.PA165.tracker.validator;

import cz.muni.fi.PA165.tracker.dto.UserDTO;
import cz.muni.fi.PA165.tracker.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.*;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private UniqueEmail annotation;

    @Autowired
    private UserFacade facade;

    @Override
    public void initialize(UniqueEmail annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null){
            return false;
        }
        try {
            // try to find user with given email, fail if you do
            UserDTO getByEmail = facade.getByEmail(value);
        } catch (Exception e){
            // is ok when nothing is found
            return true;
        }
        return false;
    }
}