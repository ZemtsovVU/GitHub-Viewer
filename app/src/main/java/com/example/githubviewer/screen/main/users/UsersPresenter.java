package com.example.githubviewer.screen.main.users;

import android.support.annotation.NonNull;

import com.example.githubviewer.model.pojo.valueobject.UserVo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

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
    public void requestUsers() {
        // TODO: Replace with data

        List<UserVo> userList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            userList.add(UserVo.newBuilder().firstName("User " + i).build());
        }

        Observable.timer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> view.setUsers(userList));
    }

    @Override
    public void requestNextUsers() {

    }

    @Override
    public void stop() {

    }
}
