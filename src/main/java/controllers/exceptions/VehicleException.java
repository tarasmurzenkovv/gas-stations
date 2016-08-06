package controllers.exceptions.vehicle;

import controllers.exceptions.GeneralServerException;

public class VehicleExistsException extends GeneralServerException {
    public VehicleExistsException(String message) {
        super(message);
    }
}
