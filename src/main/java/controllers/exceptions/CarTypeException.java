package controllers.exceptions;

public class CarTypeException extends RuntimeException {
    public CarTypeException(String message) {
        super(message);
    }
}
