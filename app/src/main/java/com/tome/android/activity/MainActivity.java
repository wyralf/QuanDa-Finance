package com.tome.android.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.tome.android.R;
import com.tome.android.factory.FragmentFactory;
import com.tome.modulebase.BaseActivity;

import butterknife.BindView;

/**
 * Created by zhangyufei
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    private FragmentManager mFragmentManager;

    @Override
    protected void init() {
        super.init();
        mFragmentManager = getSupportFragmentManager();
        mBottomBar.setOnTabSelectListener(mOnTabSelectListener);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    private OnTabSelectListener mOnTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, FragmentFactory.getInstance().getFragment(tabId)).commit();
        }
    };
}
