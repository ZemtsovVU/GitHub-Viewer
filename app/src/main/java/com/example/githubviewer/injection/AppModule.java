package com.example.githubviewer.injection;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    protected Application provideApplication() {
        return application;
    }

    @Singleton
    @Provides
    protected Context provideContext() {
        return application.getApplicationContext();
    }
}
