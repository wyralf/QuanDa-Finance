package com.tome.android.fragment;

import android.support.v7.widget.RecyclerView;

import com.tome.android.R;

import butterknife.BindView;

/**
 * Created by zhangyufei
 */
public class ListFragment extends BaseFragment{
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_list;
    }
}
