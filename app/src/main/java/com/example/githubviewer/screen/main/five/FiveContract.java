package com.example.githubviewer.screen.main.five;

import com.example.githubviewer.BasePresenter;
import com.example.githubviewer.BaseView;

public interface FiveContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void onButtonClick();
    }
}
