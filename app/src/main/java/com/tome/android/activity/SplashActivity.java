package com.tome.android.activity;

import android.os.Handler;

import com.tome.android.R;
import com.tome.android.presenter.SplashPresenter;
import com.tome.android.presenterimpl.SplashPresenterImpl;
import com.tome.android.view.SplashView;

/**
 * Created by zhangyufei
 */
public class SplashActivity extends BaseActivity implements SplashView{

    private static final int DELAY_TIME = 2000;

    private Handler mHandler = new Handler();

    private SplashPresenter mSplashPresenter;

    @Override
    public void init() {
        mSplashPresenter = new SplashPresenterImpl(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    public void onLoggedIn() {
        startActivity(MainActivity.class);
    }

    @Override
    public void onNoLogin() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(LoginActivity.class);
            }
        }, DELAY_TIME);
    }
}
