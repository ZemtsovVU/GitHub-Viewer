package com.example.githubviewer.screen.main.repos;

import android.support.annotation.NonNull;

public class ReposPresenter implements ReposContract.Presenter {
    @NonNull
    private ReposContract.View view;

    public ReposPresenter(@NonNull ReposContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void stop() {

    }

    @Override
    public void onButtonClick() {
        view.showMessage("Click");
    }
}
