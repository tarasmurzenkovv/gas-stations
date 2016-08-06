package controllers.exceptions.gasstation;

import controllers.exceptions.GeneralServerException;

public class GasStationException extends GeneralServerException {
    public GasStationException(String message) {
        super(message);
    }
}
