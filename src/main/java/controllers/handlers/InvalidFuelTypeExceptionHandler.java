package controllers.handlers;

import controllers.exceptions.InvalidFuelTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class InvalidFuelTypeExceptionHandler {
    private final static String GENERAL_ERROR_LABEL_NAME = "globalServerError";

    @ExceptionHandler(InvalidFuelTypeException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleGasStationExistsException(InvalidFuelTypeException e) {
        Map<String, String> errorFieldAndErrorMessage = new HashMap<>();
        errorFieldAndErrorMessage.put(GENERAL_ERROR_LABEL_NAME, e.getMessage());
        return new ResponseEntity<>(errorFieldAndErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
