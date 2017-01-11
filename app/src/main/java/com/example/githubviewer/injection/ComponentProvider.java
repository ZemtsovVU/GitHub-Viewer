package com.example.githubviewer.injection;

import android.app.Application;

public final class ComponentProvider {
    private static volatile ComponentProvider instance;

    private AppComponent appComponent;

    public static ComponentProvider getInstance() {
        if (instance == null) {
            synchronized (ComponentProvider.class) {
                if (instance == null) {
                    instance = new ComponentProvider();
                }
            }
        }
        return instance;
    }

    public void init(Application application) {
        AppModule appModule = new AppModule(application);

        appComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .build();
    }

    public AppComponent getAppComponent() {
        checkInitialized(appComponent);
        return appComponent;
    }

    private void checkInitialized(Object component) {
        if (component == null) {
            throw new ComponentProviderNotInitializedException();
        }
    }
}
