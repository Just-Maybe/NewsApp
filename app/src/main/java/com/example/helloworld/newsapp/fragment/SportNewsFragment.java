package com.example.helloworld.newsapp.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.helloworld.newsapp.R;
import com.example.helloworld.newsapp.adapter.SocialNewsAdapter;
import com.example.helloworld.newsapp.base.BaseMVPFragment;
import com.example.helloworld.newsapp.entity.News;
import com.example.helloworld.newsapp.mvp.presenter.SocialNewsPresenter;
import com.example.helloworld.newsapp.mvp.presenter.SportNewsPresenter;
import com.example.helloworld.newsapp.mvp.view.ISportFragmentView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by helloworld on 2017/8/28.
 */

public class SportNewsFragment extends BaseMVPFragment<ISportFragmentView, SportNewsPresenter> implements ISportFragmentView {
    private static final String TAG = SocialNewsFragment.class.getSimpleName();
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private SocialNewsAdapter adapter;
    private int lastVisibileItem;
    private GridLayoutManager layoutManager;
    private boolean isLoadMore;
    private int pageNum = 10;

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
        setRecyclerViewScoll();
    }

    /**
     * 关联 presenter
     *
     * @return
     */
    @Override
    protected SportNewsPresenter createPresenter() {
        return new SportNewsPresenter(getContext());
    }


    /**
     * 首次加载数据
     *
     * @param newsList
     */
    @Override
    public void showSportNewsData(List<News> newsList) {
        adapter = new SocialNewsAdapter(newsList, getContext());
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        pageNum = pageNum + 10;
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 加载更多数据
     *
     * @param newsList
     */
    @Override
    public void loadMoreSportNewsData(List<News> newsList) {
        adapter.loadMoreData(newsList);
        pageNum = pageNum + 10;
        isLoadMore = false;
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

    /**
     * 设置下拉刷新
     *
     * @param view
     */
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
     * 设置懒加载 第一页不需要懒加载
     */
    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
            }
        });
        mPresenter.loadData();
        isPrepared = false;
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
