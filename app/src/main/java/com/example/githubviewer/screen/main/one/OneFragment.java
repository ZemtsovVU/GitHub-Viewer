package com.example.githubviewer.screen.main.one;

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

@SuppressWarnings("CodeBlock2Expr")
public class OneFragment extends BaseFragment implements OneContract.View {
    @BindView(R.id.button)
    protected Button button;

    private OneContract.Presenter presenter;

    public static OneFragment newInstance() {
        return new OneFragment();
    }

    @Override
    public void setPresenter(@NonNull OneContract.Presenter presenter) {
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
        return inflater.inflate(R.layout.fragment_one, container, false);
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
