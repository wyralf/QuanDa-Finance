package com.tome.android.view;

/**
 * Created by zhangyufei
 */
public interface RegisterView {
    void onStartRegister();

    void onRegisterError();

    void onResisterUserExist();

    void onRegisterSuccess();

    void onUserNameError();

    void onPasswordError();

    void onConfirmPasswordError();
}
