package com.example.githubviewer.screen.userdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.example.githubviewer.R;
import com.example.githubviewer.model.pojo.valueobject.UserVo;
import com.example.githubviewer.screen.base.BaseActivity;

import butterknife.BindView;

public class UserDetailsActivity extends BaseActivity {
    private static final String TAG = UserDetailsActivity.class.getSimpleName();
    private static final String ARG_USER = TAG + "_ARG_USER";

    @BindView(R.id.container_view_group)
    protected ViewGroup containerViewGroup;

    public static void start(Context context, UserVo user) {
        Intent starter = new Intent(context, UserDetailsActivity.class);
        starter.putExtra(ARG_USER, user);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        showHomeAsUp();
        initContentView();
    }

    private void initContentView() {
        UserDetailsFragment fragment = (UserDetailsFragment) getSupportFragmentManager()
                .findFragmentByTag(UserDetailsFragment.class.getSimpleName());

        if (fragment == null) {
            UserVo user = (UserVo) getIntent().getSerializableExtra(ARG_USER);
            fragment = UserDetailsFragment.newInstance(user);
            new UserDetailsPresenter(fragment);
            showFragment(containerViewGroup.getId(), fragment);
        }
    }
}
