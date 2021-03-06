package com.example.githubviewer.screen.main.repos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.githubviewer.R;
import com.example.githubviewer.screen.main.BaseMainFragment;

public class ReposFragment extends BaseMainFragment implements ReposContract.View {
    private ReposContract.Presenter presenter;

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
        getActivity().setTitle(R.string.repos_screen_title);
        initListeners();
    }

    private void initListeners() {
        registerSecondClickReceiver(() -> {

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
//        Snackbar.make(button, message, Snackbar.LENGTH_SHORT).show();
    }
}
