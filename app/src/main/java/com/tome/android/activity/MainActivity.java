package com.tome.android.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.tome.android.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangyufei
 */
public class MainActivity extends BaseActivity{
    @BindView(R.id.btn_info_list)
    Button mMainPage;
    @BindView(R.id.btn_setting)
    Button mMyHome;

    private Fragment[] fragments;
    @Override
    protected void init() {
        super.init();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.btn_info_list, R.id.btn_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_info_list:
                startActivity(RegisterActivity.class, false);
                break;
            case R.id.btn_setting:
                startActivity(RegisterActivity.class, false);
                break;
        }
    }
}
