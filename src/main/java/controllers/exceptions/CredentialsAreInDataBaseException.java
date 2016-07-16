package controllers.exceptions;

public class CredentialsAreInDataBaseException extends GeneralServerException {
    public CredentialsAreInDataBaseException(String message) {
        super(message);
    }
}
