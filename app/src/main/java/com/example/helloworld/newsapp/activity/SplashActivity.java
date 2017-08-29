package com.example.helloworld.newsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.example.helloworld.newsapp.R;
import com.example.helloworld.newsapp.widget.SplashLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by helloworld on 2017/8/27.
 */

public class SplashActivity extends AppCompatActivity {
    @Bind(R.id.splashLayout)
    SplashLayout splashLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //状态栏与toolbar 同色
        splashLayout.addListener(new SplashLayout.onClickListener() {
            @Override
            public void onFinish() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onClickBackgroud(View v) {

            }
        });
    }
}
