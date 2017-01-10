package com.example.githubviewer.screen.base;

import android.support.annotation.NonNull;

public interface BaseView<P extends BasePresenter> {

    void setPresenter(@NonNull P presenter);

    boolean isActive();
}
