package com.example.helloworld.newsapp.network;

import com.example.helloworld.newsapp.entity.Result;
import com.example.helloworld.newsapp.entity.News;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by helloworld on 2017/8/22.
 */

public interface NewsApi {
//    http://api.tianapi.com/social/?key=APIKEY&num=10

    //移动网络新闻
    @GET("mobile/?key=dd254f020f8f732117a575519de3b6db")
    Observable<Result<News>> getSocialNews(@Query("num") int pageNum);


    //    http://api.tianapi.com/wxnew/?key=APIKEY&num=10
    //微信公众文章
    @GET("it/?key=dd254f020f8f732117a575519de3b6db")
    Observable<Result<News>> getWenxinNews(@Query("num") int pageNum);

    //微信公众文章
    @GET("vr/?key=dd254f020f8f732117a575519de3b6db")
    Observable<Result<News>> getBannerNews(@Query("num") int pageNum);

    //体育新闻文章
    @GET("tiyu/?key=dd254f020f8f732117a575519de3b6db")
    Observable<Result<News>> getSportNews(@Query("num") int pageNum);
}

