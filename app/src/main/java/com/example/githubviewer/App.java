package com.example.githubviewer;

import android.app.Application;

import com.example.githubviewer.injection.ComponentProvider;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initComponentProvider();
    }

    private void initComponentProvider() {
        ComponentProvider.getInstance().init(this);
    }
}
