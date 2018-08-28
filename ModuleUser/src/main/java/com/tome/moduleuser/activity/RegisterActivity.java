package com.tome.moduleuser.activity;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chenenyu.router.annotation.Route;
import com.tome.modulebase.control.BaseActivity;
import com.tome.modulebase.Constant.RouterConstants;
import com.tome.moduleuser.R;
import com.tome.moduleuser.R2;
import com.tome.moduleuser.presenter.RegisterPresenter;
import com.tome.moduleuser.presenter.RegisterPresenterImpl;
import com.tome.moduleuser.view.RegisterView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangyufei
 */
@Route(value = RouterConstants.ROUTER_REGISTER)
public class RegisterActivity extends BaseActivity implements RegisterView {
    @BindView(R2.id.user_name)
    EditText mUserName;
    @BindView(R2.id.password)
    EditText mPassword;
    @BindView(R2.id.confirm_password)
    EditText mConfirmPassword;
    @BindView(R2.id.register)
    Button mRegister;

    private RegisterPresenter mRegisterPresenter;
    @Override
    protected void init() {
        super.init();
        mRegisterPresenter = new RegisterPresenterImpl(this);
        mConfirmPassword.setOnEditorActionListener(mOnEditorActionListener);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    public void onStartRegister() {
        showProgress(getString(R.string.registering));
    }

    @Override
    public void onRegisterError() {
        hideProgress();
        toast(getString(R.string.register_failed));
    }

    @Override
    public void onResisterUserExist() {
        hideProgress();
        toast(getString(R.string.register_failed_user_exist));
    }

    @Override
    public void onRegisterSuccess() {
        hideProgress();
        toast(getString(R.string.register_success));
        startActivity(this, RouterConstants.ROUTER_LOGIN);
    }

    @Override
    public void onUserNameError() {
        mUserName.setError(getString(R.string.user_name_error));
    }

    @Override
    public void onPasswordError() {
        mPassword.setError(getString(R.string.user_password_error));
    }

    @Override
    public void onConfirmPasswordError() {
        mConfirmPassword.setError(getString(R.string.user_password_confirm_error));
    }

    private TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                register();
                return true;
            }
            return false;
        }
    };

    private void register() {
        hideKeyBoard();
        String userName = mUserName.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String confirmPassword = mConfirmPassword.getText().toString().trim();
        mRegisterPresenter.register(userName, password, confirmPassword);
    }

    @OnClick(R2.id.register)
    public void onClick() {
        register();
    }
}
