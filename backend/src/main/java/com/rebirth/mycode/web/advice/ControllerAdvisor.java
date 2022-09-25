package com.rebirth.mycode.web.advice;

import com.rebirth.mycode.exceptions.BadValidationException;
import com.rebirth.mycode.exceptions.EntityDoesntExistException;
import com.rebirth.mycode.exceptions.JsonParsingException;
import com.rebirth.mycode.web.advice.errorsdto.BadValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadValidationException.class)
    public ResponseEntity<BadValidationResponse> handleBadValidationValue(
            BadValidationException badValidationException, WebRequest request
    ) {
        String entityName = badValidationException.getEntityName();
        String message = "La entidad %s no esta recibiendo los parametros adecuados".formatted(entityName);
        BadValidationResponse badValidationResponse = new BadValidationResponse(entityName, message, badValidationException.getValidation());
        return ResponseEntity.badRequest().body(badValidationResponse);
    }


    @ExceptionHandler(EntityDoesntExistException.class)
    public ResponseEntity<Map<String, Object>> handleEntityDoesntExistException(
            EntityDoesntExistException entityDoesntExistException, WebRequest request
    ) {
        String message = entityDoesntExistException.getMessage();
        Map<String, Object> data = new HashMap<>();
        data.put("msg", message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
    }

    @ExceptionHandler(JsonParsingException.class)
    public ResponseEntity<Map<String, Object>> handleJsonParsingException(
            JsonParsingException jsonParsingException, WebRequest request
    ) {
        String message = jsonParsingException.getMessage();
        Map<String, Object> data = new HashMap<>();
        data.put("msg", message);
        data.put("payload", jsonParsingException.getPayload());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
    }


}
