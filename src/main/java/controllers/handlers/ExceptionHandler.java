package controllers.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {
    private final static String ERROR_SUFFIX = "Error";

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        String errorFieldName;

        Map<String, String> errorMessagesForFields = new HashMap<>();

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        for(ObjectError objectError:errors){
            errorFieldName =  ((FieldError) objectError).getField() + ERROR_SUFFIX;
            errorMessagesForFields.put(errorFieldName, objectError.getDefaultMessage());
        }
        return new ResponseEntity<>(errorMessagesForFields, HttpStatus.NOT_FOUND);
    }
}
