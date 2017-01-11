package com.example.githubviewer.model.data.exception;

public class ApiResponseException extends RuntimeException {

    public ApiResponseException(String detailMessage) {
        super(detailMessage);
    }

    public ApiResponseException(Throwable throwable) {
        super(throwable);
    }

    public ApiResponseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
