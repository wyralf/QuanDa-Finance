package com.tome.main.factory;

import com.tome.main.R;
import com.tome.modulebase.BaseFragment;
import com.tome.main.fragment.ListFragment;
import com.tome.moduleuser.MyTabFragment;

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
        if(id == R.id.list){
            return getListFragment();
        }else if(id == R.id.my){
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

