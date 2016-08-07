package controllers.exceptions;

public class FuelingException extends RuntimeException{
    public FuelingException(String message) {
        super(message);
    }
}
