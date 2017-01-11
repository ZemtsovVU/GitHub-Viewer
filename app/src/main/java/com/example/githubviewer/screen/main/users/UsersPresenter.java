package com.example.githubviewer.screen.main.users;

import android.support.annotation.NonNull;

public class UsersPresenter implements UsersContract.Presenter {
    @NonNull
    private UsersContract.View view;

    public UsersPresenter(@NonNull UsersContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
