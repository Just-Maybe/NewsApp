package com.example.helloworld.newsapp.mvp.view;

import com.example.helloworld.newsapp.entity.News;

import java.util.List;

/**
 * Created by helloworld on 2017/8/28.
 */

public interface ISportFragmentView {
    void showSportNewsData(List<News> newsList);

    void loadMoreSportNewsData(List<News> newsList);
}
