package com.example.githubviewer.screen.exception;

public class NotImplementedException extends RuntimeException {
    private static final String ERROR = "%s interface must be implemented in %s";

    public NotImplementedException(String interfaceName, String className) {
        super(String.format(ERROR, interfaceName, className));
    }
}
