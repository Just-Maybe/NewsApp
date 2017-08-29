package com.example.helloworld.newsapp.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by helloworld on 2017/8/22.
 */

public class News {

    /**
     * ctime : 2017-08-09 00:31
     * title : 辟谣：九寨沟县地震部分谣言网上流传 请大家不信谣不传谣
     * description : 搜狐社会
     * picUrl : http://photocdn.sohu.com/20170809/Img505992955_ss.jpeg
     * url : http://news.sohu.com/20170809/n505992954.shtml
     */
    @SerializedName("ctime")
    private String ctime;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("picUrl")
    private String picUrl;
    @SerializedName("url")
    private String url;

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
