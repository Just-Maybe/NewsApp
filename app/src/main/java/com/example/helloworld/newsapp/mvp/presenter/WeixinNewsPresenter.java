package com.example.helloworld.newsapp.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.helloworld.newsapp.base.BasePresenter;
import com.example.helloworld.newsapp.entity.News;
import com.example.helloworld.newsapp.entity.Result;
import com.example.helloworld.newsapp.mvp.view.IWeixinFragmentView;
import com.example.helloworld.newsapp.network.ApiConstants;
import com.example.helloworld.newsapp.network.RetrofitHelper;
import com.example.helloworld.newsapp.network.NewsApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helloworld on 2017/8/25.
 */

public class WeixinNewsPresenter extends BasePresenter<IWeixinFragmentView> {
    private static final String TAG = WeixinNewsPresenter.class.getSimpleName();
    private List<News> addList;
    IWeixinFragmentView weixinFragmentView;
    private Context context;

    public List<News> getBannList() {
        return bannList;
    }

    public void setBannList(List<News> bannList) {
        this.bannList = bannList;
    }

    private List<News> bannList;

    public WeixinNewsPresenter(Context context) {
        this.context = context;
    }

    /**
     * 显示数据
     */
    public void loadData() {
        Observable<Result<News>> observable = RetrofitHelper.createApi(NewsApi.class, ApiConstants.BASEURL).getWenxinNews(10);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<News>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Result<News> NewsResult) {
                        weixinFragmentView = getView();
                        if (NewsResult.getCode() == 200) {
                            weixinFragmentView.showWeixinNewsData(NewsResult.getNewslist());
                        } else {
//                            weixinFragmentView.showError();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 加载Banner图片
     */
    public void loadBannerData() {
        Observable<Result<News>> observable = RetrofitHelper.getNewsApi().getBannerNews(5);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<News>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Result<News> NewsResult) {
                        weixinFragmentView = getView();
                        if (NewsResult.getCode() == 200) {
//                            weixinFragmentView.showWeixinNewsData(NewsResult.getNewslist());
                            bannList = NewsResult.getNewslist();
                        } else {
//                            weixinFragmentView.showError();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        loadData();
                    }
                });
    }

    /**
     * 加载更多数据
     *
     * @param pageNum
     */
    public void loadMoreData(final int pageNum) {
        Log.d(TAG, "loadMoreData: " + pageNum);
        addList = new ArrayList<>();
        Observable<Result<News>> observable = RetrofitHelper.createApi(NewsApi.class, ApiConstants.BASEURL).getSocialNews(pageNum);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<News>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Result<News> NewsResult) {
                        weixinFragmentView = getView();
                        if (NewsResult.getCode() == 200) {
                            addList = changeData(NewsResult.getNewslist(), pageNum);
                            weixinFragmentView.loadMoreWeixinNewsData(addList);
                        }
//                        weixinFragmentView.showError();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 只添加新增的
     * 重复的不添加
     *
     * @param
     */
    private List changeData(List<News> newslist, int pageNum) {
        List<News> addList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            addList.add(i, newslist.get(pageNum - 10 + i));
        }
        return addList;
    }
}
