package com.example.githubviewer.screen.base;

import android.support.annotation.NonNull;

public interface BaseView<P> {

    void setPresenter(@NonNull P presenter);

    boolean isActive();
}
