package com.janfranco.bookstore.helpers;

public class UnknownTypeException extends Exception {

    public UnknownTypeException() {
        super("Unknown type");
    }

    public UnknownTypeException(String errorMessage) {
        super(errorMessage);
    }

}
