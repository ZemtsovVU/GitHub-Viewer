package com.example.githubviewer.screen.main.three;

import android.support.annotation.NonNull;

public class ThreePresenter implements ThreeContract.Presenter {
    @NonNull
    private ThreeContract.View view;

    public ThreePresenter(@NonNull ThreeContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void onButtonClick() {
        view.showMessage("Click");
    }
}
