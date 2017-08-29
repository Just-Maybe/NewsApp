package com.example.helloworld.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.helloworld.newsapp.R;
import com.example.helloworld.newsapp.activity.Html5Activity;
import com.example.helloworld.newsapp.entity.News;
import com.example.helloworld.newsapp.utils.GlideImageLoad;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by helloworld on 2017/8/25.
 */

public class WeixinNewsAdapter extends RecyclerView.Adapter {
    private List<News> newsList;
    private List<News> bannerList;
    private Context context;
    private static final int FOOTERVIEW = 539;
    private static final int ITEMVIEW = 139;
    private static final int BANNER = 300;
    private List<String> imgList = new ArrayList<>();
    private List<String> imgTitleList = new ArrayList<>();
    private SocialNewsAdapter.onClickListener listener;

    public WeixinNewsAdapter(List<News> newsList, List<News> bannerList, Context context) {
        this.newsList = newsList;
        this.context = context;
        this.bannerList = bannerList;
        initBanner();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOTERVIEW;
        } else if (position == 0) {
            return BANNER;
        } else {
            return ITEMVIEW;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_weixin_news, parent, false);
        View footView = LayoutInflater.from(context).inflate(R.layout.loading_view, parent, false);
        View bannerView = LayoutInflater.from(context).inflate(R.layout.banner_layout, parent, false);
        if (viewType == ITEMVIEW) {
            return new itemHolder(itemView);
        } else if (viewType == FOOTERVIEW) {
            return new footerHolder(footView);
        } else if (viewType == BANNER) {
            return new bannerHolder(bannerView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof itemHolder) {
            Glide.with(context).load(newsList.get(position).getPicUrl()).into(((itemHolder) holder).newsImg);
            ((itemHolder) holder).newsTitle.setText(newsList.get(position).getTitle());
            ((itemHolder) holder).newsTime.setText(newsList.get(position).getCtime());
            ((itemHolder) holder).newsType.setText(newsList.get(position).getDescription());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToActivity(position);
//                    listener.onClick(position);
                }
            });
        } else if (holder instanceof footerHolder) {
            ((footerHolder) holder).loadingTv.setText("上滑加载更多...");
        } else if (holder instanceof bannerHolder) {
            ((bannerHolder) holder).banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            ((bannerHolder) holder).banner.setImageLoader(new GlideImageLoad());
            ((bannerHolder) holder).banner.setImages(imgList);
            ((bannerHolder) holder).banner.setBannerTitles(imgTitleList);
            ((bannerHolder) holder).banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {

                }
            });
            ((bannerHolder) holder).banner.start();
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size() + 1;
    }

    static class itemHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.newsImg)
        ImageView newsImg;
        @Bind(R.id.newsTitle)
        TextView newsTitle;
        @Bind(R.id.newsTime)
        TextView newsTime;
        @Bind(R.id.newsType)
        TextView newsType;

        public itemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class footerHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.loadingTv)
        TextView loadingTv;

        public footerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class bannerHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.banner)
        Banner banner;

        public bannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void loadMoreData(List<News> addNewsList) {
        newsList.addAll(addNewsList);
        notifyDataSetChanged();
    }

    private void initBanner() {
        for (int i = 0; i < bannerList.size(); i++) {
            imgList.add(bannerList.get(i).getPicUrl());
            imgTitleList.add(bannerList.get(i).getTitle());
        }
    }

    public void addOnItemClickListener(SocialNewsAdapter.onClickListener listener) {
        this.listener = listener;
    }

    interface onClickListener {
        void onClick(int position);
    }

    public void goToActivity(int position) {
        Intent intent = new Intent(context.getApplicationContext(), Html5Activity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", newsList.get(position).getUrl());
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
    }
}
