package com.example.githubviewer.model.data.exception;

public class NoInternetException extends RuntimeException {
    private static final String ERROR_NO_INTERNET = "There is no internet connection.";

    public NoInternetException() {
        super(ERROR_NO_INTERNET);
    }
}
