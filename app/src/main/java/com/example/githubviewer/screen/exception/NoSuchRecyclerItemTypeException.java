package com.example.githubviewer.screen.exception;

public class NoSuchRecyclerItemTypeException extends RuntimeException {
    private static final String ERROR = "There is no such item type.";

    public NoSuchRecyclerItemTypeException() {
        super(ERROR);
    }
}
