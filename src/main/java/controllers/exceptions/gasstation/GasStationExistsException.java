package controllers.exceptions.gasstation;

import controllers.exceptions.GeneralServerException;

public class GasStationExistsException extends GeneralServerException {
    public GasStationExistsException(String message) {
        super(message);
    }
}
