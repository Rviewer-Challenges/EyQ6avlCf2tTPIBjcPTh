package com.rebirth.mycode.exceptions;

public class JsonParsingException extends RuntimeException {

    private final String payload;

    public JsonParsingException(Throwable cause, String payload) {
        super(cause);
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
}
