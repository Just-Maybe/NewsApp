package com.example.helloworld.newsapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by helloworld on 2017/8/22.
 */

public class NewsApp extends Application {
    private static NewsApp mInstance;

    public static NewsApp getInstance() {
        return mInstance;
    }

    public Context getAppContext() {
        return getApplicationContext();
    }
}
