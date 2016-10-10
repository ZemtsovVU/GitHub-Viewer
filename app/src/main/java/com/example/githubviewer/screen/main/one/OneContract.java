package com.example.githubviewer.screen.main.one;

import com.example.githubviewer.base.BasePresenter;
import com.example.githubviewer.base.BaseView;

public interface OneContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void onButtonClick();
    }
}
