package com.example.helloworld.newsapp.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.helloworld.newsapp.NewsApp;
import com.example.helloworld.newsapp.R;

import butterknife.ButterKnife;

/**
 * Created by helloworld on 2017/8/23.
 */

public abstract class BaseMVPFragment<V, T extends BasePresenter<V>> extends Fragment {
    protected T mPresenter;
    protected ProgressDialog progressDialog;
    protected SwipeRefreshLayout mRefreshLayout;
    protected boolean isVisible;
    protected boolean isPrepared;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle(getString(R.string.tip));
        progressDialog.setCancelable(true);
        progressDialog.setMessage(getString(R.string.loading));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(createViewLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        if (isSetRefresh()) {
            setupSwipeRefresh(rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        isPrepared=true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        ButterKnife.unbind(this);
    }

    protected abstract int createViewLayoutId();

    protected abstract void initView(View rootView);

    protected abstract T createPresenter();


    public boolean isSetRefresh() {
        return true;
    }

    /**
     * 设置下拉刷新
     *
     * @param view
     */
    public void setupSwipeRefresh(View view) {
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        if (mRefreshLayout != null) {
            mRefreshLayout.setColorSchemeResources(R.color.DarkPrimaryColor, R.color.PrimaryColor);
            mRefreshLayout.setProgressViewOffset(true, 0, (int) TypedValue
                    .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        }
    }

    /**
     * 设置Fragment 懒加载数据
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisible =true;
            onVisible();
        }else {
            isVisible=false;
            onInvisible();
        }
    }

    protected  void onVisible(){
        lazyLoad();
    }

    protected abstract void lazyLoad();

    /**
     * fragment显示时才加载数据
     */
    protected abstract void onInvisible();
}
