package com.example.githubviewer.screen.main.four;

import android.support.annotation.NonNull;

public class FourPresenter implements FourContract.Presenter {
    @NonNull
    private FourContract.View view;

    public FourPresenter(@NonNull FourContract.View view) {
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
