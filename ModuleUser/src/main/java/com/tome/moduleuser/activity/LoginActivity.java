package com.tome.moduleuser.activity;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tome.modulebase.BaseActivity;
import com.tome.moduleuser.R;
import com.tome.moduleuser.R2;
import com.tome.moduleuser.presenter.LoginPresenter;
import com.tome.moduleuser.presenter.LoginPresenterImpl;
import com.tome.moduleuser.view.LoginView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangyufei
 */
public class LoginActivity extends BaseActivity implements LoginView {
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 0;
    @BindView(R2.id.user_name)
    EditText mUserName;
    @BindView(R2.id.password)
    EditText mPassword;
    @BindView(R2.id.login)
    Button mLogin;
    @BindView(R2.id.new_user)
    TextView mNewUser;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void init() {
        super.init();
        mLoginPresenter = new LoginPresenterImpl(this);
        mPassword.setOnEditorActionListener(mOnEditorActionListener);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @OnClick({R2.id.login, R2.id.new_user})
    public void onClick(View view) {
        int i = view.getId();
        if(i == R.id.login){
            startLogin();
        }else if(i == R.id.new_user){
            startActivity(RegisterActivity.class, false);
        }
    }

    private void startLogin() {
        if (hasWriteExternalStoragePermission()) {
            login();
        } else {
            applyPermission();
        }
    }

    private boolean hasWriteExternalStoragePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED;
    }

    private void applyPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
    }

    private void login() {
        hideKeyBoard();
        String userName = mUserName.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        mLoginPresenter.login(userName, password);
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
    public void onStartLogin() {
        showProgress(getString(R.string.logining));
    }

    @Override
    public void onLoginSuccess() {
        hideProgress();
        toast(getString(R.string.login_success));
        //startActivity(MainActivity.class);
    }

    @Override
    public void onLoginFailed() {
        hideProgress();
        toast(getString(R.string.login_failed));
    }

    private TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                startLogin();
                return true;
            }
            return false;
        }
    };

    /**
     * 申请权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_EXTERNAL_STORAGE:
                if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                    login();
                } else {
                    toast(getString(R.string.not_get_permission));
                }
                break;
        }
    }
}
