package com.example.githubviewer.injection;

import android.content.Context;

import com.example.githubviewer.R;
import com.example.githubviewer.model.data.source.GitHubApi;
import com.example.githubviewer.util.ConnectionUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module
public class ApiModule {
    private static final int CONNECT_TIMEOUT_SECONDS = 15;
    private static final int READ_TIMEOUT_SECONDS = 15;
    private static final int WRITE_TIMEOUT_SECONDS = 15;

    @Singleton
    @Provides
    protected GitHubApi provideGitHubApiInterface(Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }

    @Singleton
    @Provides
    protected Retrofit provideRetrofit(Context context, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    protected OkHttpClient provideOkHttpClient(Cache cache,
                                               Interceptor cacheInterceptor,
                                               HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    protected Cache provideHttpCache(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(httpCacheDirectory, cacheSize);
    }

    @Singleton
    @Provides
    protected Interceptor provideCacheInterceptor(Context context) {
        return chain -> {
            int maxAge = 60 * 5; // read from cache for 5 minute
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            Request originalRequest = chain.request();
            String cacheHeaderValue = ConnectionUtil.isOnline(context)
                    ? "public, max-age=" + maxAge
                    : "public, only-if-cached, max-stale=" + maxStale;
            Request request = originalRequest.newBuilder().build();
            Response response = chain.proceed(request);
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", cacheHeaderValue)
                    .build();
        };
    }

    @Singleton
    @Provides
    protected HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logInterceptor;
    }
}
