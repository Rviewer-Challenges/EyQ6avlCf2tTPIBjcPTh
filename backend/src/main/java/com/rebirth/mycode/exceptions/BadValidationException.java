package com.rebirth.mycode.exceptions;

import com.rebirth.mycode.web.advice.errorsdto.BadValidationValue;

import java.util.List;

public class BadValidationException extends RuntimeException {

    private final String entityName;
    private final List<BadValidationValue> validation;

    public BadValidationException(String entityName, List<BadValidationValue> validation) {
        super("Error en la validacion de esta entidad");
        this.entityName = entityName;
        this.validation = validation;
    }

    public String getEntityName() {
        return entityName;
    }

    public List<BadValidationValue> getValidation() {
        return validation;
    }
}
