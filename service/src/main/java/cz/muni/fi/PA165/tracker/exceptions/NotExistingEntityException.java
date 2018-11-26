package cz.muni.fi.PA165.tracker.exceptions;

/**
 * Exception for not existing entity.
 * @author pmikova 433345
 */
public class NotExistingEntityException extends RuntimeException {
    public NotExistingEntityException(String s) {
        super(s);
    }

    public NotExistingEntityException(String s, Throwable cause) {
        super(s, cause);
    }
}
