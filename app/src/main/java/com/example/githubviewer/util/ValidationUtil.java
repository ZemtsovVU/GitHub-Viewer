package com.example.githubviewer.util;

import android.text.TextUtils;
import android.util.Patterns;

@SuppressWarnings("ALL")
public final class ValidationUtil {

    public static boolean hasEmptyFields(String... fields) {
        for (String field : fields) {
            if (TextUtils.isEmpty(field)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
