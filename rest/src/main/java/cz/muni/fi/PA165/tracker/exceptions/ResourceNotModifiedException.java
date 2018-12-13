package cz.muni.fi.PA165.tracker.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResourceNotModifiedException is raised in case we requested an update and the resource was not modified.
 * @author pmikova 433345
 */
@ResponseStatus(value = HttpStatus.NOT_MODIFIED, reason="The requested resource was not modified")
public class ResourceNotModifiedException extends RuntimeException {
    public ResourceNotModifiedException(Throwable cause){
        super(cause);
    }
}