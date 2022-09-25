package com.rebirth.mycode.web.rest;

import com.rebirth.mycode.exceptions.BadValidationException;
import com.rebirth.mycode.web.advice.errorsdto.BadValidationValue;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class BaseController {

    public URI createUri4newEntity(UUID uuid) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .build(uuid);
    }

    public void validateDto(String entityName, Errors errors) {
        if (errors.hasErrors()) {
            List<BadValidationValue> validationValues = errors.getFieldErrors().stream().map((fieldError -> {
                String field = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                Object rejectValue = fieldError.getRejectedValue();
                return new BadValidationValue(field, message, rejectValue);
            })).collect(Collectors.toList());
            throw new BadValidationException(entityName, validationValues);
        }
    }


}
