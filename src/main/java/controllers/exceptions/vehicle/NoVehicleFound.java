package controllers.exceptions.vehicle;

import controllers.exceptions.GeneralServerException;

public class NoVehicleFound extends GeneralServerException {

    public NoVehicleFound(String message) {
        super(message);
    }
}
