package com.tome.modulebase.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.chenenyu.router.Router;

import butterknife.ButterKnife;

/**
 * Created by zhangyufei
 */
public abstract class BaseActivity extends AppCompatActivity{
    public static final String TAG = "BaseActivity";

    private InputMethodManager mInputMethodManager;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        init();
    }

    protected void init() {}

    public abstract int getLayoutRes();

    /*
     * activity的跳转采用这种形式
     */
    protected void startActivity(Context context, String routerName) {
        startActivity(context, routerName, true);
    }

    protected void startActivity(Context context, String routerName, boolean finish) {
        Router.build(routerName).go(context);
        if (finish) {
            finish();
        }
    }


    protected void hideKeyBoard() {
        if (mInputMethodManager == null) {
            mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        mInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    protected void showProgress(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    protected void hideProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
