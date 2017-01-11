package com.example.githubviewer.model.data.mapper;

import android.content.Context;

import com.example.githubviewer.injection.ComponentProvider;
import com.example.githubviewer.model.data.exception.ApiResponseException;
import com.example.githubviewer.model.data.exception.NoInternetException;
import com.example.githubviewer.model.data.exception.UnauthorizedException;
import com.example.githubviewer.model.pojo.serviceobject.ResponseSo;
import com.example.githubviewer.util.ConnectionUtil;

import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import rx.functions.Func1;

public class ApiResponseMapper<R extends ResponseSo> implements Func1<Result<R>, R> {
    private static final int HTTP_CODE_UNAUTHORIZED = 401;
    private static final int HTTP_CODE_FORBIDDEN = 403;

    @Override
    public R call(Result<R> responseResult) {
        if (responseResult.isError()) {
            // No internet, etc.
            handleInternetException(responseResult.error());
        } else if (!responseResult.response().isSuccessful()) {
            // HTTP Codes = (200 - 299)
            handleHttpException(responseResult.response());
        }
        // TODO: Добавить обработку софт исключений
//        else if (!responseResult.response().body().isSuccess()) {
//            // Soft api exceptions
//            handleResultException(responseResult.response().body().getException());
//        }
        return responseResult.response().body();
    }

    private void handleInternetException(Throwable throwable) {
        Context context = ComponentProvider.getInstance().getAppComponent().getContext();
        if (!ConnectionUtil.isOnline(context)) {
            throw new NoInternetException();
        } else {
            throw new ApiResponseException(throwable);
        }
    }

    private void handleHttpException(Response<R> response) {
        int responseCode = response.raw().code();
        if (responseCode == HTTP_CODE_UNAUTHORIZED || responseCode == HTTP_CODE_FORBIDDEN) {
            throw new UnauthorizedException();
        } else {
            throw new ApiResponseException(response.raw().message());
        }
    }

//    private void handleResultException(Exception exception) {
//        throw new ApiResponseException(exception.getMessage());
//    }
}
