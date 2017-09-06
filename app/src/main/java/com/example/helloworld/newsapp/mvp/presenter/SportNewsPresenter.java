package com.example.helloworld.newsapp.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.helloworld.newsapp.base.BasePresenter;
import com.example.helloworld.newsapp.entity.News;
import com.example.helloworld.newsapp.entity.Result;
import com.example.helloworld.newsapp.mvp.view.ISportFragmentView;
import com.example.helloworld.newsapp.network.ApiConstants;
import com.example.helloworld.newsapp.network.NewsApi;
import com.example.helloworld.newsapp.network.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helloworld on 2017/8/28.
 */

public class SportNewsPresenter extends BasePresenter<ISportFragmentView> {
    private static final String TAG = SportNewsPresenter.class.getSimpleName();
    private Context context;
    private List<News> newsList;
    ISportFragmentView sportFragmentView;
    private List<News> addList;

    public SportNewsPresenter(Context context) {
        this.context = context;
    }
    /**
     * 显示数据
     */
    public void loadData() {
        Observable<Result<News>> observable = RetrofitHelper.getNewsApi().getSportNews(10);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<News>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Result<News> socialNewsResult) {
                        sportFragmentView = getView();
                        if (socialNewsResult.getCode() == 200) {
                            sportFragmentView.showSportNewsData(socialNewsResult.getNewslist());
                        } else {
//                            sportFragmentView.showError();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
//                        sportFragmentView.hideProgressBar();
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
                    public void onNext(@NonNull Result<News> socialNewsResult) {
                        sportFragmentView = getView();
//                        sportFragmentView.showProgressBar();
                        if (socialNewsResult.getCode() == 200) {
                            addList = changeData(socialNewsResult.getNewslist(), pageNum);
                            sportFragmentView.loadMoreSportNewsData(addList);
                        }
//                        socialFragmentView.showError();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
//                        sportFragmentView.hideProgressBar();
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
