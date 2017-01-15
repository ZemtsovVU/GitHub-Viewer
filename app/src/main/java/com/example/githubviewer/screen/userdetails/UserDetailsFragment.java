package com.example.githubviewer.screen.userdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.githubviewer.R;
import com.example.githubviewer.model.pojo.valueobject.UserVo;
import com.example.githubviewer.screen.base.BaseFragment;
import com.example.githubviewer.util.PreconditionsUtil;

public class UserDetailsFragment extends BaseFragment implements UserDetailsContract.View {
    private static final String TAG = UserDetailsFragment.class.getSimpleName();
    private static final String ARG_USER = TAG + "_ARG_USER";

    private UserVo user;

    private UserDetailsContract.Presenter presenter;

    public static UserDetailsFragment newInstance(UserVo user) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);

        UserDetailsFragment fragment = new UserDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(@NonNull UserDetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (UserVo) getArguments().getSerializable(ARG_USER);
        PreconditionsUtil.checkNotNull(UserVo.class, user);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("User Details"); // TODO: 15.01.2017
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
}
