package pl.milton.model.exception;

public class ApiUnstableException extends RuntimeException {

    public ApiUnstableException(String reason) {
        super(reason);
    }

}