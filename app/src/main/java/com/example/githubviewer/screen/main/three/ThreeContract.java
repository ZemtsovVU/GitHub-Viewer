package com.example.githubviewer.screen.main.three;

import com.example.githubviewer.base.BasePresenter;
import com.example.githubviewer.base.BaseView;

public interface ThreeContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void onButtonClick();
    }
}
