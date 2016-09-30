package com.example.githubviewer.screen.main;

import android.support.v4.app.Fragment;

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

public final class MenuFactory {

    private static MenuFactory instance;

    public static MenuFactory getInstance() {
        if (instance == null) {
            synchronized (MenuFactory.class) {
                if (instance == null) {
                    instance = new MenuFactory();
                }
            }
        }
        return instance;
    }

    /**
     * Создаются фрагменты и их презентеры, соответствующие пунктам меню R.menu.tabs.
     */
    public Fragment getFragment(int bottomNavigationPosition) {
        if (bottomNavigationPosition == 0) {
            OneFragment fragment = OneFragment.newInstance();
            new OnePresenter(fragment);
            return fragment;
        } else if (bottomNavigationPosition == 1) {
            TwoFragment fragment = TwoFragment.newInstance();
            new TwoPresenter(fragment);
            return fragment;
        } else if (bottomNavigationPosition == 2) {
            ThreeFragment fragment = ThreeFragment.newInstance();
            new ThreePresenter(fragment);
            return fragment;
        } else if (bottomNavigationPosition == 3) {
            FourFragment fragment = FourFragment.newInstance();
            new FourPresenter(fragment);
            return fragment;
        } else if (bottomNavigationPosition == 4) {
            FiveFragment fragment = FiveFragment.newInstance();
            new FivePresenter(fragment);
            return fragment;
        } else {
            // Если ни одна позиция не совпадает, то возвращается первый фрагмент.
            OneFragment fragment = OneFragment.newInstance();
            new OnePresenter(fragment);
            return fragment;
        }
    }
}
