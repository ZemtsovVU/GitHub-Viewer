package com.example.githubviewer.screen.main.users;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.githubviewer.R;
import com.example.githubviewer.model.pojo.valueobject.UserVo;
import com.example.githubviewer.screen.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

@SuppressWarnings("CodeBlock2Expr")
public class UsersFragment extends BaseFragment implements UsersContract.View {
    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    private UsersRecyclerArbitraryRowAdapter adapter;

    private UsersContract.Presenter presenter;

    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public void setPresenter(@NonNull UsersContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.stop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initListeners();
    }

    private void initViews() {
        adapter = new UsersRecyclerArbitraryRowAdapter();

        List<UserVo> userList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            userList.add(UserVo.newBuilder().firstName("User " + i).build());
        }

        Observable.timer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    adapter.setUsers(userList);
                });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void initListeners() {

    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
