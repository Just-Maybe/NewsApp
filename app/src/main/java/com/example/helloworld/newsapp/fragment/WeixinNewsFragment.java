package com.example.helloworld.newsapp.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworld.newsapp.R;
import com.example.helloworld.newsapp.adapter.SocialNewsAdapter;
import com.example.helloworld.newsapp.adapter.WeixinNewsAdapter;
import com.example.helloworld.newsapp.base.BaseMVPFragment;
import com.example.helloworld.newsapp.entity.News;
import com.example.helloworld.newsapp.mvp.presenter.WeixinNewsPresenter;
import com.example.helloworld.newsapp.mvp.view.IWeixinFragmentView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by helloworld on 2017/8/25.
 */

public class WeixinNewsFragment extends BaseMVPFragment<IWeixinFragmentView, WeixinNewsPresenter> implements IWeixinFragmentView {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private int pageNum;
    private LinearLayoutManager layoutManager;
    private int lastVisibileItem;
    private boolean isLoadMore;
    private WeixinNewsAdapter adapter;

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(View rootView) {
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
            }
        });
        mPresenter.loadBannerData();
        setRecyclerViewScoll();
    }

    @Override
    protected WeixinNewsPresenter createPresenter() {
        return new WeixinNewsPresenter(getContext());
    }


    @Override
    protected void lazyLoad() {
//        if (!isPrepared || !isVisible) {
//            return;
//        }
//        mRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                mRefreshLayout.setRefreshing(true);
//            }
//        });
//        isPrepared = false;
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void setupSwipeRefresh(View view) {
        super.setupSwipeRefresh(view);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 10;
                mPresenter.loadData();
            }
        });
    }

    /**
     * 设置上拉加载
     */
    public void setRecyclerViewScoll() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == recyclerView.SCROLL_STATE_IDLE) {
                    lastVisibileItem = layoutManager.findLastVisibleItemPosition();
                    if (layoutManager.getItemCount() == 1) {
                        return;
                    }
                    if (lastVisibileItem + 1 == layoutManager.getItemCount() && !isLoadMore) {
                        isLoadMore = true;
                        mPresenter.loadMoreData(pageNum);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void showWeixinNewsData(List<News> newsList) {
        adapter = new WeixinNewsAdapter(newsList, mPresenter.getBannList(), getContext());
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        pageNum = pageNum + 10;
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void loadMoreWeixinNewsData(List<News> newsList) {
        adapter.loadMoreData(newsList);
        pageNum = pageNum + 10;
        isLoadMore = false;
    }
}
