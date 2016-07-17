package controllers.handlers;

import controllers.exceptions.BusinessCustomerServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BusinessCustomerServiceExceptionHandler {

    private final static String GENERAL_ERROR_LABEL_NAME = "globalServerError";

    @ExceptionHandler(BusinessCustomerServiceException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleNoFuelingRecordsWereFoundException(BusinessCustomerServiceException e) {
        Map<String, String> errorFieldAndErrorMessage = new HashMap<>();
        errorFieldAndErrorMessage.put(GENERAL_ERROR_LABEL_NAME, e.getMessage());
        return new ResponseEntity<>(errorFieldAndErrorMessage, HttpStatus.NOT_FOUND);
    }
}
