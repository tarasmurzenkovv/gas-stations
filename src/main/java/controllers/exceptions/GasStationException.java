package controllers.exceptions;

public class GasStationException extends RuntimeException {
    public GasStationException(String message) {
        super(message);
    }
}
