package com.example.githubviewer.screen.main.three;

import com.example.githubviewer.screen.base.BasePresenter;
import com.example.githubviewer.screen.base.BaseView;

public interface ThreeContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void onButtonClick();
    }
}
