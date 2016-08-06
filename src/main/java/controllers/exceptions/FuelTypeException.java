package controllers.exceptions;

public class FuelTypeException extends IllegalArgumentException {
    public FuelTypeException(String message) {
        super(message);
    }
}
