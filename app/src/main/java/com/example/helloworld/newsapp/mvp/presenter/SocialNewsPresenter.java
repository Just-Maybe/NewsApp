package com.example.helloworld.newsapp.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.helloworld.newsapp.base.BasePresenter;
import com.example.helloworld.newsapp.entity.Result;
import com.example.helloworld.newsapp.entity.News;
import com.example.helloworld.newsapp.mvp.view.ISocialFragmentView;
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
 * Created by helloworld on 2017/8/23.
 */

public class SocialNewsPresenter extends BasePresenter<ISocialFragmentView> {

    private static final String TAG = SocialNewsPresenter.class.getSimpleName();
    private Context context;
    ISocialFragmentView socialFragmentView;
    private List<News> addList;

    public SocialNewsPresenter(Context context) {
        this.context = context;
    }

    /**
     * 显示数据
     */
    public void loadData() {
        Observable<Result<News>> observable = RetrofitHelper.createApi(NewsApi.class, ApiConstants.BASEURL).getSocialNews(10);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<News>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Result<News> socialNewsResult) {
                        socialFragmentView = getView();
                        socialFragmentView.showProgressBar();
                        if (socialNewsResult.getCode() == 200) {
                            socialFragmentView.showSocialNewsData(socialNewsResult.getNewslist());
                        } else {
                            socialFragmentView.showError();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        socialFragmentView.hideProgressBar();
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
        Observable<Result<News>> observable = RetrofitHelper.getNewsApi().getSocialNews(pageNum);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<News>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Result<News> socialNewsResult) {
                        socialFragmentView = getView();
                        socialFragmentView.showProgressBar();
                        if (socialNewsResult.getCode() == 200) {
                            addList = changeData(socialNewsResult.getNewslist(), pageNum);
                            socialFragmentView.loadMoreSocialNewsData(addList);
                        }
                        socialFragmentView.showError();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        socialFragmentView.hideProgressBar();
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
