package com.example.githubviewer.screen.exception;

public class NoSuchMenuPositionException extends RuntimeException {
    private static final String ERROR = "There is no such menu position.";

    public NoSuchMenuPositionException() {
        super(ERROR);
    }
}
