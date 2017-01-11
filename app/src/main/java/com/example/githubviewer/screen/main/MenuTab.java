package com.example.githubviewer.screen.main;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.example.githubviewer.R;
import com.example.githubviewer.screen.exception.NoSuchMenuPositionException;
import com.example.githubviewer.screen.main.about.AboutFragment;
import com.example.githubviewer.screen.main.about.AboutPresenter;
import com.example.githubviewer.screen.main.repos.ReposFragment;
import com.example.githubviewer.screen.main.repos.ReposPresenter;
import com.example.githubviewer.screen.main.users.UsersFragment;
import com.example.githubviewer.screen.main.users.UsersPresenter;

public enum MenuTab {
    USERS {
        @Override
        public int position() {
            return 0;
        }

        @Override
        public int title() {
            return R.string.tab_users;
        }

        @Override
        public int drawable() {
            return R.mipmap.ic_launcher;
        }

        @Override
        public int color() {
            return R.color.tab_one;
        }

        @Override
        public Fragment fragment() {
            UsersFragment fragment = UsersFragment.newInstance();
            new UsersPresenter(fragment);
            return fragment;
        }
    },
    REPOS {
        @Override
        public int position() {
            return 1;
        }

        @Override
        public int title() {
            return R.string.tab_repos;
        }

        @Override
        public int drawable() {
            return R.mipmap.ic_launcher;
        }

        @Override
        public int color() {
            return R.color.tab_two;
        }

        @Override
        public Fragment fragment() {
            ReposFragment fragment = ReposFragment.newInstance();
            new ReposPresenter(fragment);
            return fragment;
        }
    },
    ABOUT {
        @Override
        public int position() {
            return 2;
        }

        @Override
        public int title() {
            return R.string.tab_about;
        }

        @Override
        public int drawable() {
            return R.mipmap.ic_launcher;
        }

        @Override
        public int color() {
            return R.color.tab_three;
        }

        @Override
        public Fragment fragment() {
            AboutFragment fragment = AboutFragment.newInstance();
            new AboutPresenter(fragment);
            return fragment;
        }
    };

    public static MenuTab get(int position) {
        for (MenuTab menuTab : MenuTab.values()) {
            if (menuTab.position() == position) {
                return menuTab;
            }
        }
        throw new NoSuchMenuPositionException();
    }

    public abstract int position();

    @StringRes
    public abstract int title();

    @DrawableRes
    public abstract int drawable();

    @ColorRes
    public abstract int color();

    public abstract Fragment fragment();
}
