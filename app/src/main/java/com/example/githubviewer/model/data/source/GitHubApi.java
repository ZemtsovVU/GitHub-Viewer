package com.example.githubviewer.model.data.source;

import com.example.githubviewer.model.pojo.datatransferobject.UserDto;

import java.util.List;

import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface GitHubApi {

    @GET("/users")
    Observable<Result<List<UserDto>>> users();

    @GET
    Observable<Result<List<UserDto>>> usersPaginate(
            @Url String url);
}
