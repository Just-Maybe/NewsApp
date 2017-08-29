package com.example.helloworld.newsapp.mvp.view;

import com.example.helloworld.newsapp.entity.News;

import java.util.List;

/**
 * Created by helloworld on 2017/8/25.
 */

public interface IWeixinFragmentView {

    void showWeixinNewsData(List<News> newsList);

    void loadMoreWeixinNewsData(List<News> newsList);
}
