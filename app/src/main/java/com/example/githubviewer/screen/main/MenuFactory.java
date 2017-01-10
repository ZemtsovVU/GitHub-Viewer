package com.example.githubviewer.screen.main;

import android.support.v4.app.Fragment;

import com.example.githubviewer.screen.exception.NoSuchMenuPositionException;
import com.example.githubviewer.screen.main.one.OneFragment;
import com.example.githubviewer.screen.main.one.OnePresenter;
import com.example.githubviewer.screen.main.three.ThreeFragment;
import com.example.githubviewer.screen.main.three.ThreePresenter;
import com.example.githubviewer.screen.main.two.TwoFragment;
import com.example.githubviewer.screen.main.two.TwoPresenter;

public final class MenuFactory {

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
        } else {
            throw new NoSuchMenuPositionException();
        }
    }
}
