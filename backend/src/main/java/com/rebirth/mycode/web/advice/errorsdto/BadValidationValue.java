package com.rebirth.mycode.web.advice.errorsdto;

import java.io.Serializable;

public class BadValidationValue implements Serializable {

    private final String field;
    private final String message;
    private final Object rejectedValue;

    public BadValidationValue(String field, String message, Object rejectedValue) {
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }
}
