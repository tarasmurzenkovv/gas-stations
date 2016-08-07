package controllers.handler;

import controllers.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ServerExceptionHandler {

    private final static String GENERAL_ERROR_LABEL_NAME = "globalServerError";
    private final static String ERROR_SUFFIX = "Error";

    @ExceptionHandler(value = {
            VehicleException.class,
            GasStationException.class,
            FuelTypeException.class,
            FuelingException.class,
            CustomerException.class,
            UsernameNotFoundException.class,
            AuthorizationException.class})
    @ResponseBody
    public ResponseEntity<Map<String, String>> serverExceptionsHandler(AuthorizationException e) {
        Map<String, String> errorFieldAndErrorMessage = new HashMap<>();
        errorFieldAndErrorMessage.put(GENERAL_ERROR_LABEL_NAME, e.getMessage());
        return new ResponseEntity<>(errorFieldAndErrorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        String errorFieldName;

        Map<String, String> errorMessagesForFields = new HashMap<>();

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        for (ObjectError objectError : errors) {
            errorFieldName = ((FieldError) objectError).getField() + ERROR_SUFFIX;
            errorMessagesForFields.put(errorFieldName, objectError.getDefaultMessage());
        }
        return new ResponseEntity<>(errorMessagesForFields, HttpStatus.NOT_FOUND);
    }
}
