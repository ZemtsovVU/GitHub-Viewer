package com.example.githubviewer.screen.main.two;

import android.support.annotation.NonNull;

public class TwoPresenter implements TwoContract.Presenter {
    @NonNull
    private TwoContract.View view;

    public TwoPresenter(@NonNull TwoContract.View view) {
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
