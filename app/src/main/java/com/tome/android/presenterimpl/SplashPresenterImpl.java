package com.tome.android.presenterimpl;

import com.hyphenate.chat.EMClient;
import com.tome.android.presenter.SplashPresenter;
import com.tome.android.view.SplashView;

/**
 * Created by zhangyufei
 */
public class SplashPresenterImpl implements SplashPresenter{
    public SplashView mSplashView;

    public SplashPresenterImpl(SplashView splashView) {
        mSplashView = splashView;
    }

    @Override
    public void checkLoginStatus() {
        if (EMClient.getInstance().isLoggedInBefore() && EMClient.getInstance().isConnected()) {
            mSplashView.onLoggedIn();
        } else {
            mSplashView.onNoLogin();
        }
    }
}
