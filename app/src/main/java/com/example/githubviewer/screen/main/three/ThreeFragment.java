package com.example.githubviewer.screen.main.three;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.githubviewer.base.BaseFragment;
import com.example.githubviewer.R;

import butterknife.BindView;

public class ThreeFragment extends BaseFragment implements ThreeContract.View {
    @BindView(R.id.button)
    protected Button button;

    private ThreeContract.Presenter presenter;

    public static ThreeFragment newInstance() {
        return new ThreeFragment();
    }

    @Override
    public void setPresenter(@NonNull ThreeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
    }

    private void initListeners() {
        button.setOnClickListener(v -> {
            presenter.onButtonClick();
        });
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(button, message, Snackbar.LENGTH_SHORT).show();
    }
}
