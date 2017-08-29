package com.example.helloworld.newsapp.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.helloworld.newsapp.R;
import com.example.helloworld.newsapp.adapter.HomeFragmentAdapter;
import com.example.helloworld.newsapp.base.BaseMVPActivity;
import com.example.helloworld.newsapp.base.BasePresenter;

import butterknife.Bind;

/**
 * Created by helloworld on 2017/8/22.
 */

public class HomeActivity extends BaseMVPActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.title);
        tabLayout.addTab(tabLayout.newTab().setText("互联网"));
        tabLayout.addTab(tabLayout.newTab().setText("科技"));
        tabLayout.addTab(tabLayout.newTab().setText("新闻"));
        viewPager.setAdapter(new HomeFragmentAdapter(fm));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        setupToolbarMenu();
    }

    private void setupToolbarMenu() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mineMenu:
                        Intent intent = new Intent(HomeActivity.this, MineActivity.class);
                        startActivity(intent);
//                        Toast.makeText(HomeActivity.this, "我的", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.searchMenu:
//                        Toast.makeText(HomeActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected int ContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
