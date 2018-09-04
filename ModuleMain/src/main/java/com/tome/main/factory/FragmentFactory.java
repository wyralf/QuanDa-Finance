package com.tome.main.factory;

import com.tome.main.R;
import com.tome.main.fragment.MainFragment;
import com.tome.modulebase.control.BaseFragment;
import com.tome.main.fragment.ListFragment;
import com.tome.main.fragment.MyTabFragment;

/**
 * Created by zhangyufei
 */
public class FragmentFactory {
    public static final String TAG = "FragmentFactory";

    private static FragmentFactory sFragmentFactory;

    private BaseFragment mListFragment;
    private BaseFragment mMytabFragment;
    private BaseFragment mMainFragment;

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
        if(id == R.id.list){
            return getListFragment();
        }else if(id == R.id.my){
            return getMytabFragment();
        }else if(id == R.id.main){
            return getMainFragment();
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

    public BaseFragment getMainFragment() {
        if (mMainFragment == null) {
            mMainFragment = new MainFragment();
        }
        return mMainFragment;
    }
}

