package com.tome.moduleuser.view;

/**
 * Created by zhangyufei
 */
public interface LoginView {
    void onUserNameError();

    void onPasswordError();

    void onStartLogin();

    void onLoginSuccess();

    void onLoginFailed();
}
