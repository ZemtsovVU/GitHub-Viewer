package com.example.githubviewer.screen.main.two;

import com.example.githubviewer.base.BasePresenter;
import com.example.githubviewer.base.BaseView;

public interface TwoContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void onButtonClick();
    }
}
