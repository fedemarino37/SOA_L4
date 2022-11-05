package com.example.TP2.repository.exception;

public class HttpUnexpectedErrorException extends Exception {
    public HttpUnexpectedErrorException() { super(); }

    public HttpUnexpectedErrorException(final Throwable cause) { super(cause); }

    public HttpUnexpectedErrorException(final String cause) { super(cause); }
}
