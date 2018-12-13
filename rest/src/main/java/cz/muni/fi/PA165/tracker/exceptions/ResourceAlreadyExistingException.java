package cz.muni.fi.PA165.tracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResourceAlreadyExistingException is raised in case some created resources already exist.
 * @author pmikova 433345
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason="The resource already exists")
public class ResourceAlreadyExistingException extends RuntimeException {
    public ResourceAlreadyExistingException(Throwable cause){
        super(cause);
    }
    public ResourceAlreadyExistingException(){};
}