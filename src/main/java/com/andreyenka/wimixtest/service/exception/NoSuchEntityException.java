package com.andreyenka.wimixtest.service.exception;

public class NoSuchEntityException extends RuntimeException{

    public NoSuchEntityException(String message) {
        super(message);
    }
}
