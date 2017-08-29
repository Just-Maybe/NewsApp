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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by helloworld on 2017/8/23.
 */

public class SocialNewsAdapter extends RecyclerView.Adapter {
    private static final String TAG = SocialNewsAdapter.class.getSimpleName();
    private List<News> newsList;
    private Context context;
    private static final int FOOTERVIEW = 539;
    private static final int ITEMVIEW = 139;
    private onClickListener listener;

    public SocialNewsAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOTERVIEW;
        } else {
            return ITEMVIEW;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_social_news, parent, false);
        View footerView = LayoutInflater.from(context).inflate(R.layout.loading_view, parent, false);
        if (viewType == FOOTERVIEW) {
            return new footerHolder(footerView);
        } else {
            return new itemHolder(itemView);
        }
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

    public void loadMoreData(List<News> addNewsList) {
        newsList.addAll(addNewsList);
        notifyDataSetChanged();
    }

    public void addOnItemClickListener(onClickListener listener) {
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
