package com.example.helloworld.newsapp.network;

import android.os.Environment;
import android.util.Log;

import com.example.helloworld.newsapp.NewsApp;
import com.example.helloworld.newsapp.utils.NetworkUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by helloworld on 2017/8/22.
 */

public class RetrofitHelper {

    private static OkHttpClient mOkHttpClient;


    private static Cache cache;

    static {
        initOkHttpClient();
    }

    public static NewsApi getNewsApi() {
        return createApi(NewsApi.class, ApiConstants.BASEURL);
    }

    /**
     * 创建Api
     *
     * @param clazz
     * @param baseUrl
     * @param <T>
     * @return
     */
    public static <T> T createApi(Class<T> clazz, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
    }


    /**
     * 初始化OkHttpClient
     */
    private static void initOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {
                    File cacheFile = new File(Environment.getDownloadCacheDirectory(), "HttpCache");
                    cache = new Cache(cacheFile, 1024 * 1024 * 10);
                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
//                            .addNetworkInterceptor(new CacheInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                    Log.d("111", String.valueOf("initOkHttpClient: " + mOkHttpClient == null));
                    Log.d("111", "initOkHttpClient: ");
                }
            }
        }
    }

    /**
     * 设置缓存策略
     */
    private static class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            //有网络时 设置缓存超时1 个小时
            int maxAge = 60 * 60;
            //无网络时，设置超时为1天
            int maxStle = 60 * 60 * 24;
            Request request = chain.request();
            if (NetworkUtils.isConnected(NewsApp.getInstance())) {
                // 有网络时只从网络缓存中获取
                request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            } else {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            if (NetworkUtils.isConnected(NewsApp.getInstance())) {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale" + maxStle)
                        .build();
            }
            return response;
        }
    }
}
