package controllers.handlers;

import controllers.exceptions.vehicle.NoVehiclesWereFound;
import controllers.exceptions.vehicle.VehicleExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class VehicleControllerExceptionHandler {

    private final static String GENERAL_ERROR_LABEL_NAME = "globalServerError";

    @org.springframework.web.bind.annotation.ExceptionHandler(NoVehiclesWereFound.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleValidationException(NoVehiclesWereFound e) {
        Map<String, String> errorFieldAndErrorMessage = new HashMap<>();
        errorFieldAndErrorMessage.put(GENERAL_ERROR_LABEL_NAME, e.getMessage());
        return new ResponseEntity<>(errorFieldAndErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(VehicleExistsException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleVehicleExistsException(VehicleExistsException e) {
        Map<String, String> errorFieldAndErrorMessage = new HashMap<>();
        errorFieldAndErrorMessage.put(GENERAL_ERROR_LABEL_NAME, e.getMessage());
        return new ResponseEntity<>(errorFieldAndErrorMessage, HttpStatus.BAD_REQUEST);
    }
}
