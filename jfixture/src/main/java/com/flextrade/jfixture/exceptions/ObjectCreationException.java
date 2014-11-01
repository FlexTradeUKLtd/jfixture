package com.flextrade.jfixture.exceptions;

public class ObjectCreationException extends RuntimeException {
    public ObjectCreationException(String message) {
        super(message);
    }

    public ObjectCreationException(String message, Exception inner) {
        super(message, inner);
    }
}
