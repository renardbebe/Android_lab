package com.renardbebe.ex9.factory;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import com.renardbebe.ex9.model.Github;
import com.renardbebe.ex9.model.Repos;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import io.reactivex.Observable;

/**
 * Created by renardbebe on 2017/12/12.
 */

public class ServiceFactory {
    public static final String BASE_URL = "https://api.github.com";

    public interface GithubService {
        /** 获取用户信息 */
        @GET("/users/{user}")
        Observable<Github> getUser(@Path("user") String user);
        /** 获取仓库信息 */
        @GET("users/{user}/repos")
        Observable<List<Repos>> getUserRepos(@Path("user") String user);
    }
    /** 创建Retrofit客户端 */
    static public Retrofit build() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        // 连接超时
        okHttpBuilder.connectTimeout(5 * 1000, TimeUnit.MILLISECONDS);
        // 读超时
        okHttpBuilder.readTimeout(5 * 1000, TimeUnit.MILLISECONDS);

        final Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        return retrofitBuilder.client(okHttpBuilder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
