package controllers.exceptions;

public class InvalidFuelTypeException extends IllegalArgumentException {
    public InvalidFuelTypeException(String message) {
        super(message);
    }
}
