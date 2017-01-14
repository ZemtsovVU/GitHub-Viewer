package com.example.githubviewer.screen.main.repos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.githubviewer.R;
import com.example.githubviewer.injection.ComponentProvider;
import com.example.githubviewer.model.data.source.GitHubApi;
import com.example.githubviewer.screen.main.BaseMainFragment;
import com.example.githubviewer.util.GitHubHeaderLinksExtractor;

import butterknife.BindView;
import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReposFragment extends BaseMainFragment implements ReposContract.View {
    private static final String TAG = ReposFragment.class.getSimpleName();

    @BindView(R.id.button)
    protected Button button;

    private ReposContract.Presenter presenter;
    private boolean firstRequest = true;
    private String nextLink;

    public static ReposFragment newInstance() {
        return new ReposFragment();
    }

    @Override
    public void setPresenter(@NonNull ReposContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repos, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
    }

    private void initListeners() {
        registerSecondClickReceiver(() -> {

        });

        button.setOnClickListener(v -> {
            GitHubApi api = ComponentProvider.getInstance().getApiComponent().getGitHubApiInterface();

            if (firstRequest) {
                api.users()
                        .map(Result::response)
                        .doOnNext(response -> {
                            firstRequest = false;
                            nextLink = GitHubHeaderLinksExtractor.extractNext(response);
                        })
                        .map(Response::body)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(userDtoList -> {
                            Log.d(TAG, "initListeners: ");
                        });
            } else {
                api.usersPaginate(nextLink)
                        .map(Result::response)
                        .doOnNext(response -> {
                            nextLink = GitHubHeaderLinksExtractor.extractNext(response);
                        })
                        .map(Response::body)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(userDtoList -> {
                            Log.d(TAG, "initListeners: ");
                        });
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(button, message, Snackbar.LENGTH_SHORT).show();
    }
}
