package com.tome.main.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.chenenyu.router.annotation.Route;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.tome.main.R;
import com.tome.main.R2;
import com.tome.main.factory.FragmentFactory;
import com.tome.modulebase.control.BaseActivity;
import com.tome.modulebase.Constant.RouterConstants;

import butterknife.BindView;

/**
 * Created by zhangyufei
 */
@Route(value = RouterConstants.ROUTER_MAIN)
public class MainActivity extends BaseActivity {
    @BindView(R2.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R2.id.bottomBar)
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
