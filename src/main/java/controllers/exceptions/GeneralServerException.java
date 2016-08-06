package controllers.exceptions;

public class GeneralServerException extends RuntimeException {
    public GeneralServerException(String message) {
        super(message);
    }
}
