package com.example.githubviewer.screen.exception;

public class NoSuchRecyclerViewTypeException extends RuntimeException {
    private static final String ERROR = "There is no such view type.";

    public NoSuchRecyclerViewTypeException() {
        super(ERROR);
    }
}
