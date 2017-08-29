package com.example.helloworld.newsapp.utils;

import android.content.Context;
import android.widget.ProgressBar;

/**
 * Created by helloworld on 2017/8/23.
 */

public class ProgressBarUtils {
    private static ProgressBarUtils instance = null;

    private ProgressBarUtils() {
    }

    public static ProgressBarUtils getInstance() {
        synchronized (ProgressBarUtils.class) {
            if (instance == null) {
                instance = new ProgressBarUtils();
            }
        }
        return instance;
    }
}
