package cz.muni.fi.PA165.tracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ConstraintViolationException should be raised in case constraints were broken
 * @author pmikova 433345
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ConstraintViolationException extends RuntimeException {
    public ConstraintViolationException(){};
    public ConstraintViolationException(Throwable cause){
        super(cause);
    }
}
