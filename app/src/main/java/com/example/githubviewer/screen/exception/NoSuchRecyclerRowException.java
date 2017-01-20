package com.example.githubviewer.screen.exception;

public class NoSuchRecyclerRowException extends RuntimeException {
    private static final String ERROR = "There is no such recycler row.";

    public NoSuchRecyclerRowException() {
        super(ERROR);
    }
}
