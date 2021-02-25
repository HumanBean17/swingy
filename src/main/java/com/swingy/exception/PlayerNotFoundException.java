package com.swingy.exception;

public class PlayerNotFoundException extends Throwable {

    @Override
    public String toString() {
        return "Player not found. Check if player exists";
    }

    @Override
    public String getMessage() {
        return "Player not found. Check if player exists";
    }
}
