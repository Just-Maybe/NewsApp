package com.example.helloworld.newsapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.helloworld.newsapp.R;

/**
 * Created by helloworld on 2017/8/28.
 */

public class SplashLayout extends RelativeLayout {
    private ImageView mSpashBg;
    private Button mJumpbtn;
    private onClickListener listener;
    private int bgResId;
    private int btnBgResId;
    private Context mContext;
    private boolean isFinish = false;

    public void addListener(onClickListener listener) {
        this.listener = listener;
    }

    public SplashLayout(Context context) {
        this(context, null);
    }

    public SplashLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public SplashLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initAttrs(context, attrs);
        initView(context);
        countDownTimer.start();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SplashLayout);
        bgResId = ta.getResourceId(R.styleable.SplashLayout_imageBgResourceId, 0);
        btnBgResId = ta.getResourceId(R.styleable.SplashLayout_buttonBgResourceId, 0);
        ta.recycle();
    }

    /**
     * 初始化 背景图片和跳过按钮
     *
     * @param context
     */
    private void initView(Context context) {
        mSpashBg = new ImageView(context);
        mSpashBg.setBackgroundResource(bgResId);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        mSpashBg.setScaleType(ImageView.ScaleType.CENTER);
        mSpashBg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && !isFinish) {
                    listener.onClickBackgroud(v);
                }
                isFinish = true;
                countDownTimer.cancel();
            }
        });
        addView(mSpashBg, lp);
        mJumpbtn = new Button(context);
        mJumpbtn.setTextColor(getResources().getColor(R.color.TextColor));
        mJumpbtn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        mJumpbtn.setBackgroundResource(btnBgResId);
        mJumpbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && !isFinish) {

                    listener.onFinish();
                }
                isFinish = true;
                countDownTimer.cancel();
            }
        });
        lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lp.setMargins(0, dp2px(context, 35), dp2px(context, 20), 0);
        addView(mJumpbtn, lp);
    }

    public int dp2px(Context context, float value) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (scale * value + 0.5f);
    }

    /**
     * 倒数计时器
     */
    CountDownTimer countDownTimer = new CountDownTimer(4000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mJumpbtn.setText("跳过(" + millisUntilFinished / 1000 + ")s");
        }

        @Override
        public void onFinish() {
            mJumpbtn.setText("跳过(0)s");
            if (listener != null && !isFinish) {
                listener.onFinish();
            }
            isFinish = true;
            this.cancel();
        }
    };

    /**
     * 回调
     */
    public interface onClickListener {
        void onFinish();    //点击跳过按钮触发事件 与 倒数到0时触发的时间一样

        void onClickBackgroud(View v);  //点击 背景图片触发的事件
    }
}