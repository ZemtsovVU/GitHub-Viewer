package com.example.githubviewer.screen.main;

import android.support.v4.app.Fragment;

import com.example.githubviewer.screen.exception.NoSuchMenuPositionException;
import com.example.githubviewer.screen.main.about.AboutFragment;
import com.example.githubviewer.screen.main.users.UsersFragment;
import com.example.githubviewer.screen.main.users.UsersPresenter;
import com.example.githubviewer.screen.main.about.AboutPresenter;
import com.example.githubviewer.screen.main.repos.ReposFragment;
import com.example.githubviewer.screen.main.repos.ReposPresenter;

public final class MenuFactory {

    public Fragment getFragment(int bottomNavigationPosition) {
        if (bottomNavigationPosition == 0) {
            UsersFragment fragment = UsersFragment.newInstance();
            new UsersPresenter(fragment);
            return fragment;
        } else if (bottomNavigationPosition == 1) {
            ReposFragment fragment = ReposFragment.newInstance();
            new ReposPresenter(fragment);
            return fragment;
        } else if (bottomNavigationPosition == 2) {
            AboutFragment fragment = AboutFragment.newInstance();
            new AboutPresenter(fragment);
            return fragment;
        } else {
            throw new NoSuchMenuPositionException();
        }
    }
}
