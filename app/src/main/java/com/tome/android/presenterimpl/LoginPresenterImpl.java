package com.tome.android.presenterimpl;

import com.hyphenate.chat.EMClient;
import com.tome.im.adapter.EMCallBackAdapter;
import com.tome.android.presenter.LoginPresenter;
import com.tome.modulebase.utils.StringUtils;
import com.tome.modulebase.utils.ThreadUtils;
import com.tome.android.view.LoginView;

/**
 * Created by zhangyufei
 */
public class LoginPresenterImpl implements LoginPresenter{
    public LoginView mLoginView;

    public LoginPresenterImpl(LoginView loginView) {
        mLoginView = loginView;
    }
    @Override
    public void login(String userName, String pwd) {
        if (StringUtils.checkUserName(userName)) {
            if (StringUtils.checkPassword(pwd)) {
                mLoginView.onStartLogin();
                startLogin(userName, pwd);
            } else {
                mLoginView.onPasswordError();
            }
        } else {
            mLoginView.onUserNameError();
        }
    }

    private void startLogin(String userName, String pwd) {
        EMClient.getInstance().login(userName, pwd, mEMCallBack);
    }

    private EMCallBackAdapter mEMCallBack = new EMCallBackAdapter() {

        @Override
        public void onSuccess() {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoginView.onLoginSuccess();
                }
            });
        }

        @Override
        public void onError(int i, String s) {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoginView.onLoginFailed();
                }
            });
        }
    };
}
