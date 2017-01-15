package com.example.githubviewer.screen.main.users;

import com.example.githubviewer.model.pojo.valueobject.UserVo;
import com.example.githubviewer.screen.base.BasePresenter;
import com.example.githubviewer.screen.base.BaseView;

import java.util.List;

public interface UsersContract {

    interface View extends BaseView<Presenter> {

        void setUsers(List<UserVo> userList);

        void addUsers(List<UserVo> userList);

        void onContentState();

        void onEmptyState();

        void hideProgressFooter();

        void scrollOnePositionUp();

        void showMessage(String message);
    }

    interface Presenter extends BasePresenter {

        void requestUsers();

        void requestNextUsers();
    }
}
