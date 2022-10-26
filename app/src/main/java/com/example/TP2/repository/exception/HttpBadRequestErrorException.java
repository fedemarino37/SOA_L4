package com.example.TP2.repository.exception;

public class HttpBadRequestErrorException extends Exception {
    public HttpBadRequestErrorException() { super(); }

    public HttpBadRequestErrorException(final Throwable cause) { super(cause); }

    public HttpBadRequestErrorException(final String cause) { super(cause); }
}
