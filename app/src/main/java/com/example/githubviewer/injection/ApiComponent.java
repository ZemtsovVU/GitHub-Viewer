package com.example.githubviewer.injection;

import com.example.githubviewer.model.data.source.GitHubApi;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {

    GitHubApi getGitHubApiInterface();
}
