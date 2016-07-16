package controllers.exceptions;

public class GeneralServerException extends RuntimeException {
    public GeneralServerException() {
    }

    public GeneralServerException(String message) {
        super(message);
    }

    public GeneralServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralServerException(Throwable cause) {
        super(cause);
    }

    public GeneralServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
