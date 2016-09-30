package com.example.githubviewer.screen.main.four;

import com.example.githubviewer.BasePresenter;
import com.example.githubviewer.BaseView;

public interface FourContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void onButtonClick();
    }
}
