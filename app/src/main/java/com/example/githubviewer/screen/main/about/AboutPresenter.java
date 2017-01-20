package com.example.githubviewer.screen.main.about;

import android.support.annotation.NonNull;

public class AboutPresenter implements AboutContract.Presenter {
    @NonNull
    private AboutContract.View view;

    public AboutPresenter(@NonNull AboutContract.View view) {
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
