package com.example.githubviewer.screen.main.five;

import android.support.annotation.NonNull;

public class FivePresenter implements FiveContract.Presenter {
    @NonNull
    private FiveContract.View view;

    public FivePresenter(@NonNull FiveContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void onButtonClick() {
        view.showMessage("Click");
    }
}
