package com.example.githubviewer.screen.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.example.githubviewer.R;
import com.example.githubviewer.base.BaseActivity;

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
        addFragment(menuFactory.getFragment(START_MENU_POSITION), containerViewGroup.getId());
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

            addFragment(menuFactory.getFragment(position), containerViewGroup.getId());
            return true;
        });
    }
}
