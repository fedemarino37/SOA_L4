package com.example.TP2.repository.exception;

public class SQLUserNotFoundException extends Exception{
    public SQLUserNotFoundException() { super(); }

    public SQLUserNotFoundException(final Throwable cause) { super(cause); }

    public SQLUserNotFoundException(final String cause) { super(cause); }
}
