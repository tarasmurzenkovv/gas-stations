package controllers.exceptions.vehicle;

import controllers.exceptions.GeneralServerException;

public class NoVehiclesWereFound extends GeneralServerException {

    public NoVehiclesWereFound(String message) {
        super(message);
    }
}
