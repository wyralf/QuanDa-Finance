package com.tome.android.factory;

import com.tome.android.R;
import com.tome.android.fragment.BaseFragment;
import com.tome.android.fragment.ListFragment;
import com.tome.android.fragment.MyTabFragment;

/**
 * Created by zhangyufei
 */
public class FragmentFactory {
    public static final String TAG = "FragmentFactory";

    private static FragmentFactory sFragmentFactory;

    private BaseFragment mListFragment;
    private BaseFragment mMytabFragment;

    public static FragmentFactory getInstance() {
        if (sFragmentFactory == null) {
            synchronized (FragmentFactory.class) {
                if (sFragmentFactory == null) {
                    sFragmentFactory = new FragmentFactory();
                }
            }
        }
        return sFragmentFactory;
    }

    public BaseFragment getFragment(int id) {
        switch (id) {
            case R.id.list:
                return getListFragment();
            case R.id.my:
                return getMytabFragment();
        }
        return null;
    }
    public BaseFragment getListFragment() {
        if (mListFragment == null) {
            mListFragment = new ListFragment();
        }
        return mListFragment;
    }

    public BaseFragment getMytabFragment() {
        if (mMytabFragment == null) {
            mMytabFragment = new MyTabFragment();
        }
        return mMytabFragment;
    }
}

