package com.example.githubviewer.screen.main.users;

import com.example.githubviewer.screen.base.BasePresenter;
import com.example.githubviewer.screen.base.BaseView;

public interface UsersContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
    }

    interface Presenter extends BasePresenter {

    }
}
