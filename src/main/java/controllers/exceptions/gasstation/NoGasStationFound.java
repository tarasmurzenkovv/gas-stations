package controllers.exceptions.gasstation;

import controllers.exceptions.GeneralServerException;

public class NoGasStationFound extends GeneralServerException {

    public NoGasStationFound(String message) {
        super(message);
    }
}
