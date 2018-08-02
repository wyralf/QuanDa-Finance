package com.tome.android.presenterimpl;

import com.tome.android.presenter.RegisterPresenter;
import com.tome.android.utils.StringUtils;
import com.tome.android.view.RegisterView;

/**
 * Created by zhangyufei
 */
public class RegisterPresenterImpl implements RegisterPresenter{
    public RegisterView mRegisterView;

    public RegisterPresenterImpl(RegisterView registerView) {
        mRegisterView = registerView;
    }
    @Override
    public void register(String userName, String pwd, String pwdConfirm) {
        if (StringUtils.checkUserName(userName)) {
            if (StringUtils.checkPassword(pwd)) {
                if (pwd.equals(pwdConfirm)) {
                    mRegisterView.onStartRegister();
                    register(userName, pwd);
                } else {
                    mRegisterView.onConfirmPasswordError();
                }
            } else {
                mRegisterView.onPasswordError();
            }
        } else {
            mRegisterView.onUserNameError();
        }
    }

    private void register(final String userName, final String pwd) {

    }
}
