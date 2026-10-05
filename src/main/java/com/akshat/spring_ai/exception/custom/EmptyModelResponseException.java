package com.akshat.spring_ai.exception.custom;

public class EmptyModelResponseException extends RuntimeException {
    public EmptyModelResponseException(String message) {
        super(message);
    }
}
