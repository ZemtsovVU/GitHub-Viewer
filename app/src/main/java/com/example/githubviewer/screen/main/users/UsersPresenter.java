package com.example.githubviewer.screen.main.users;

import android.support.annotation.NonNull;

import com.example.githubviewer.injection.ComponentProvider;
import com.example.githubviewer.model.data.source.GitHubApi;
import com.example.githubviewer.model.pojo.valueobject.mapper.UserDtoVoMapper;
import com.example.githubviewer.util.GitHubHeaderLinksExtractor;
import com.example.githubviewer.util.L;

import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UsersPresenter implements UsersContract.Presenter {
    private GitHubApi api;
    private String nextUsersLink;

    private Subscription usersSubscription;

    private boolean lastPage;

    @NonNull
    private UsersContract.View view;

    public UsersPresenter(@NonNull UsersContract.View view) {
        this.view = view;
        this.view.setPresenter(this);

        api = ComponentProvider.getInstance().getApiComponent().getGitHubApiInterface();
    }

    @Override
    public void requestUsers() {
        if (usersSubscription != null) {
            usersSubscription.unsubscribe();
        }

        usersSubscription = api.users()
                .map(Result::response)
                .doOnNext(response ->
                        nextUsersLink = GitHubHeaderLinksExtractor.extractNext(response))
                .map(Response::body)
                .flatMap(Observable::from)
                .map(new UserDtoVoMapper())
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userVoList -> {
                            view.setUsers(userVoList);
                            usersSubscription.unsubscribe();
                        },
                        throwable -> {
                            view.setUsers(null);
                            usersSubscription.unsubscribe();

                            L.printStackTrace(throwable);
                            view.showMessage(throwable.getLocalizedMessage()); // TODO: Заменить на пустое состояние
                        }
                );
    }

    @Override
    public void requestNextUsers() {
        boolean loading = !usersSubscription.isUnsubscribed();
        if (loading || lastPage) {
            return;
        }

        usersSubscription = api.usersPaginate(nextUsersLink)
                .map(Result::response)
                .doOnNext(response ->
                        nextUsersLink = GitHubHeaderLinksExtractor.extractNext(response))
                .map(Response::body)
                .flatMap(Observable::from)
                .map(new UserDtoVoMapper())
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userVoList -> {
                            if (userVoList == null || userVoList.isEmpty()) {
                                lastPage = true;
                            }

                            view.addUsers(userVoList);
                            usersSubscription.unsubscribe();
                        },
                        throwable -> {
                            view.setUsers(null);
                            usersSubscription.unsubscribe();

                            L.printStackTrace(throwable);
                            view.showMessage(throwable.getLocalizedMessage()); // TODO: Заменить на пустое состояние
                        }
                );
    }

    @Override
    public void stop() {
        usersSubscription.unsubscribe();
    }
}
