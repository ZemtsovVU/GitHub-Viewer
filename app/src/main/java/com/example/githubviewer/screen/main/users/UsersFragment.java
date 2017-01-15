package com.example.githubviewer.screen.main.users;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

import com.example.githubviewer.R;
import com.example.githubviewer.model.pojo.valueobject.UserVo;
import com.example.githubviewer.screen.main.BaseMainFragment;
import com.example.githubviewer.screen.userdetails.UserDetailsActivity;

import java.util.List;

import butterknife.BindView;

public class UsersFragment extends BaseMainFragment implements UsersContract.View {
    private static final int CONTENT_STATE = 0;
    private static final int EMPTY_STATE = 1;

    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.view_animator)
    protected ViewAnimator viewAnimator;
    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    private UsersRecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;

    private UsersContract.Presenter presenter;

    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public void setPresenter(@NonNull UsersContract.Presenter presenter) {
        this.presenter = presenter;
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
        getActivity().setTitle(R.string.users_screen_title);
        initViews();
        initListeners();
    }

    private void initViews() {
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_one, R.color.refresh_two,
                R.color.refresh_three, R.color.refresh_four, R.color.refresh_five);

        adapter = new UsersRecyclerAdapter();
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initListeners() {
        registerSecondClickReceiver(() -> recyclerView.smoothScrollToPosition(0));

        swipeRefreshLayout.setOnRefreshListener(() -> presenter.requestUsers());

        adapter.asAdObservable().subscribe(ad -> {
            showMessage(getString(R.string.show_ad_message));
        });

        adapter.asUserObservable().subscribe(user -> {
            UserDetailsActivity.start(getContext(), user);
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastPosition = layoutManager.getItemCount() - 1;
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                boolean lastItem = lastVisiblePosition == lastPosition;
                if (lastItem) {
                    presenter.requestNextUsers();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.requestUsers();
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
    public void setUsers(List<UserVo> userList) {
        adapter.setUsers(userList);
        recyclerView.scrollToPosition(0);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addUsers(List<UserVo> userList) {
        adapter.addUsers(userList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onContentState() {
        viewAnimator.setDisplayedChild(CONTENT_STATE);
    }

    @Override
    public void onEmptyState() {
        viewAnimator.setDisplayedChild(EMPTY_STATE);
    }

    @Override
    public void hideProgressFooter() {
        adapter.hideFooterProgress();
    }

    @Override
    public void scrollOnePositionUp() {
        int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
        layoutManager.scrollToPositionWithOffset(firstVisiblePosition, 0);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
    }
}
