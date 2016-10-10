package com.example.githubviewer.util;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

@SuppressWarnings("ALL")
public final class KeyboardUtil {

    public static void showKeyboard(@NonNull Activity activity, @NonNull View anchorView) {
        anchorView.requestFocus();

        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(anchorView, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void hideKeyboard(@NonNull Activity activity, @NonNull View anchorView) {
        IBinder windowToken = anchorView.getWindowToken();

        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void preventShowingKeyboard(@NonNull Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
