package com.flextrade.jfixture.exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(String message, Exception inner) {
        super(message, inner);
    }
}
