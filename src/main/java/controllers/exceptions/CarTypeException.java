package controllers.exceptions;

public class NoCarTypeWasFound extends GeneralServerException {
    public NoCarTypeWasFound(String message) {
        super(message);
    }
}
