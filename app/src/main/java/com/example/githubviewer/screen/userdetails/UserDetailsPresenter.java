package com.example.githubviewer.screen.userdetails;

import android.support.annotation.NonNull;

public class UserDetailsPresenter implements UserDetailsContract.Presenter {
    @NonNull
    private UserDetailsContract.View view;

    public UserDetailsPresenter(@NonNull UserDetailsContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void stop() {

    }
}
