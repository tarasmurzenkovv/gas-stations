package controllers.handlers;

import controllers.exceptions.CredentialsAreInDataBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AuthorisationControllerExceptionHandler {

    private final static String GENERAL_ERROR_LABEL_NAME = "globalServerError";

    @org.springframework.web.bind.annotation.ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleNoFuelingRecordsWereFoundException(UsernameNotFoundException e) {
        Map<String, String> errorFieldAndErrorMessage = new HashMap<>();
        errorFieldAndErrorMessage.put(GENERAL_ERROR_LABEL_NAME, e.getMessage());
        return new ResponseEntity<>(errorFieldAndErrorMessage, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CredentialsAreInDataBaseException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> credentialsAreInDataBasesException(CredentialsAreInDataBaseException e) {
        Map<String, String> errorFieldAndErrorMessage = new HashMap<>();
        errorFieldAndErrorMessage.put(GENERAL_ERROR_LABEL_NAME, e.getMessage());
        return new ResponseEntity<>(errorFieldAndErrorMessage, HttpStatus.NOT_FOUND);
    }
}
