package com.rebirth.mycode.web.advice.errorsdto;

import java.io.Serializable;
import java.util.List;

public class BadValidationResponse implements Serializable {

    private final String entity;
    private final String error;
    private final List<BadValidationValue> bads;

    public BadValidationResponse(String entity, String error, List<BadValidationValue> bads) {
        this.entity = entity;
        this.error = error;
        this.bads = bads;
    }

    public String getError() {
        return error;
    }

    public String getEntity() {
        return entity;
    }

    public List<BadValidationValue> getBads() {
        return bads;
    }
}
