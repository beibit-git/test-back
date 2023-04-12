package kz.test.exception.domain;

import kz.test.exception.ExceptionDescription;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message) {
        super(message);
    }

    public CustomNotFoundException(String entity, String byWhat) {
        super(String.format(ExceptionDescription.CustomNotFoundException, entity, byWhat));
    }
}
