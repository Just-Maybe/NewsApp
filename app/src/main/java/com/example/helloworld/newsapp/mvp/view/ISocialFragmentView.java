package com.example.helloworld.newsapp.mvp.view;

import com.example.helloworld.newsapp.entity.News;

import java.util.List;

/**
 * Created by helloworld on 2017/8/23.
 */

public interface ISocialFragmentView {
    void showProgressBar();

    void hideProgressBar();

    void showError();

    void showSocialNewsData(List<News> newsList);

    void loadMoreSocialNewsData(List<News> newsList);
}
