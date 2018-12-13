package cz.muni.fi.PA165.tracker.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * InvalidParameterException is raised in case invalid parameters were entered.
 * @author pmikova 433345
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(Throwable cause){
        super(cause);
    }
    public InvalidParameterException(){};

}