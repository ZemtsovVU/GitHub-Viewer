package com.example.githubviewer.screen.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.githubviewer.R;
import com.example.githubviewer.screen.base.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import rx.Observable;

public class MainActivity extends BaseActivity implements SecondClickRegistrator {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.container_view_group)
    protected ViewGroup containerViewGroup;
    @BindView(R.id.bottom_navigation)
    protected AHBottomNavigation bottomNavigation;

    private List<SecondClickReceiver> secondClickReceiverList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initBottomNavigation();

        showFragment(containerViewGroup.getId(), MenuTab.USERS.fragment());
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        setTitle("Main Screen");
    }

    private void initBottomNavigation() {
        List<AHBottomNavigationItem> tabs = Arrays.asList(
                new AHBottomNavigationItem(
                        MenuTab.USERS.title(), MenuTab.USERS.drawable(), MenuTab.USERS.color()),
                new AHBottomNavigationItem(
                        MenuTab.REPOS.title(), MenuTab.REPOS.drawable(), MenuTab.REPOS.color()),
                new AHBottomNavigationItem(
                        MenuTab.ABOUT.title(), MenuTab.ABOUT.drawable(), MenuTab.ABOUT.color())
        );
        bottomNavigation.addItems(tabs);

        bottomNavigation.setColored(true);
        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            if (wasSelected) {
                Observable.from(secondClickReceiverList)
                        .subscribe(SecondClickReceiver::onSecondClick);
                return false;
            }

            showFragment(containerViewGroup.getId(), MenuTab.get(position).fragment());
            return true;
        });
    }

    @Override
    protected void showFragment(@IdRes int container, @NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(container, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void registerSecondClickReceiver(SecondClickReceiver secondClickReceiver) {
        secondClickReceiverList.add(secondClickReceiver);
    }

    @Override
    public void unregisterSecondClickReceiver(SecondClickReceiver secondClickReceiver) {
        secondClickReceiverList.remove(secondClickReceiver);
    }
}
