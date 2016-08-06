package controllers.exceptions;

public class AuthorizationException extends GeneralServerException {
    public AuthorizationException(String message) {
        super(message);
    }
}
