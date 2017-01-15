package com.example.githubviewer.util;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

@SuppressWarnings("ALL")
public final class ViewUtil {

    public static void toggleEnableState(@NonNull ViewGroup rootViewGroup, boolean enabled) {
        for (int i = 0; i < rootViewGroup.getChildCount(); i++) {
            View child = rootViewGroup.getChildAt(i);
            child.setEnabled(enabled);
            if (child instanceof ViewGroup) {
                toggleEnableState((ViewGroup) child, enabled);
            }
        }
    }
}
