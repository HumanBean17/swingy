package com.swingy.exception;

public class ConnectionFailedException extends Throwable {

    @Override
    public String toString() {
        return "Connect to database failed.";
    }

    @Override
    public String getMessage() {
        return "Connect to database failed.";
    }
}
