package com.example.githubviewer.screen.main.one;

import android.support.annotation.NonNull;

public class OnePresenter implements OneContract.Presenter {
    @NonNull
    private OneContract.View view;

    public OnePresenter(@NonNull OneContract.View view) {
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
