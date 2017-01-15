package com.example.githubviewer.util;

@SuppressWarnings("ALL")
public final class PreconditionsUtil {

    public static <T> void checkNotNull(Class<T> dataClass, T dataObject) {
        if (dataObject == null) {
            throw new NullPointerException(dataClass.getSimpleName() + " cannot be null.");
        }
    }
}
