package cz.muni.fi.PA165.tracker.exceptions;

public class NotExistingEntityException extends RuntimeException {
    public NotExistingEntityException(String s) {
        super(s);
    }

    public NotExistingEntityException(String s, Throwable cause) {
        super(s, cause);
    }
}
