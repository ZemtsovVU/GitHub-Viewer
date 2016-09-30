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

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int START_MENU_POSITION = 0;

    @BindView(R.id.container_view_group)
    protected ViewGroup containerViewGroup;
    @BindView(R.id.bottom_navigation)
    protected AHBottomNavigation bottomNavigation;

    private MenuFactory menuFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuFactory = new MenuFactory();

        initBottomNavigation();
        showFragment(menuFactory.getFragment(START_MENU_POSITION));
    }

    private void initBottomNavigation() {
        int[] colors = getResources().getIntArray(R.array.tab_colors);
        AHBottomNavigationAdapter adapter = new AHBottomNavigationAdapter(this, R.menu.tabs);
        adapter.setupWithBottomNavigation(bottomNavigation, colors);

        bottomNavigation.setColored(true);
        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            if (wasSelected) {
                return false;
            }

            showFragment(menuFactory.getFragment(position));
            return true;
        });
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(containerViewGroup.getId(), fragment)
                .commit();
    }
}
