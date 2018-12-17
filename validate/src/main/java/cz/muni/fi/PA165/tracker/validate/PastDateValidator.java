package cz.muni.fi.PA165.tracker.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Past Date Validator
 * @author pmikova 433345
 */
public class PastDateValidator implements ConstraintValidator<PastDate, LocalDate> {

    private PastDate annotation;

    @Override
    public void initialize(PastDate annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(LocalDate annotatedObject, ConstraintValidatorContext context) {
        if (annotatedObject == null) {
            return false;
        }
        return annotatedObject.isBefore(LocalDate.now());
    }
}
