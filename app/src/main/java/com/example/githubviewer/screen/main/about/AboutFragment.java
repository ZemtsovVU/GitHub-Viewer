package com.example.githubviewer.screen.main.about;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.githubviewer.R;
import com.example.githubviewer.screen.main.BaseMainFragment;

public class AboutFragment extends BaseMainFragment implements AboutContract.View {
    private AboutContract.Presenter presenter;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public void setPresenter(@NonNull AboutContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.about_screen_title);
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
