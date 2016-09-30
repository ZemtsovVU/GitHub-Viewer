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
