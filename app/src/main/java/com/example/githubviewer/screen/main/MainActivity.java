package com.example.githubviewer.screen.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.example.githubviewer.BaseActivity;
import com.example.githubviewer.R;
import com.example.githubviewer.screen.main.five.FiveFragment;
import com.example.githubviewer.screen.main.five.FivePresenter;
import com.example.githubviewer.screen.main.four.FourFragment;
import com.example.githubviewer.screen.main.four.FourPresenter;
import com.example.githubviewer.screen.main.one.OneFragment;
import com.example.githubviewer.screen.main.one.OnePresenter;
import com.example.githubviewer.screen.main.three.ThreeFragment;
import com.example.githubviewer.screen.main.three.ThreePresenter;
import com.example.githubviewer.screen.main.two.TwoFragment;
import com.example.githubviewer.screen.main.two.TwoPresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.container_view_group)
    protected ViewGroup containerViewGroup;
    @BindView(R.id.bottom_navigation)
    protected AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBottomNavigation();
    }

    private void initBottomNavigation() {
        int[] colors = getResources().getIntArray(R.array.bottom_colors);
        AHBottomNavigationAdapter adapter = new AHBottomNavigationAdapter(this, R.menu.bottom);
        adapter.setupWithBottomNavigation(bottomNavigation, colors);

        bottomNavigation.setColored(true);
        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            if (position == 0) {
                showOneFragment();
            } else if (position == 1) {
                showTwoFragment();
            } else if (position == 2) {
                showThreeFragment();
            } else if (position == 3) {
                showFourFragment();
            } else if (position == 4) {
                showFiveFragment();
            }
            return true;
        });
        bottomNavigation.setCurrentItem(0, true);
    }

    private void showOneFragment() {
        OneFragment fragment = (OneFragment) getSupportFragmentManager()
                .findFragmentByTag(OneFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = OneFragment.newInstance();
        }
        showFragment(fragment);

        new OnePresenter(fragment);
    }

    private void showTwoFragment() {
        TwoFragment fragment = (TwoFragment) getSupportFragmentManager()
                .findFragmentByTag(TwoFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = TwoFragment.newInstance();
        }
        showFragment(fragment);

        new TwoPresenter(fragment);
    }

    private void showThreeFragment() {
        ThreeFragment fragment = (ThreeFragment) getSupportFragmentManager()
                .findFragmentByTag(ThreeFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = ThreeFragment.newInstance();
        }
        showFragment(fragment);

        new ThreePresenter(fragment);
    }

    private void showFourFragment() {
        FourFragment fragment = (FourFragment) getSupportFragmentManager()
                .findFragmentByTag(FourFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = FourFragment.newInstance();
        }
        showFragment(fragment);

        new FourPresenter(fragment);
    }

    private void showFiveFragment() {
        FiveFragment fragment = (FiveFragment) getSupportFragmentManager()
                .findFragmentByTag(FiveFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = FiveFragment.newInstance();
        }
        showFragment(fragment);

        new FivePresenter(fragment);
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(containerViewGroup.getId(), fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}
