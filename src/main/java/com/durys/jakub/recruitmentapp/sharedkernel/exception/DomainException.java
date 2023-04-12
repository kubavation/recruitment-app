package com.durys.jakub.recruitmentapp.sharedkernel.exception;

public class DomainException extends RuntimeException {

    public DomainException() {}

    public DomainException(String message) {
        super(message);
    }
}
