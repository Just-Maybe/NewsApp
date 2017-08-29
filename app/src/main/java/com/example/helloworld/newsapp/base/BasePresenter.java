package com.example.helloworld.newsapp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by helloworld on 2017/8/22.
 */

public class BasePresenter<V> {
    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
