package com.tome.main.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chenenyu.router.Router;
import com.tome.main.R;
import com.tome.main.R2;
import com.tome.main.adapter.CreditListAdapter;
import com.tome.main.presenter.CreditPresenter;
import com.tome.main.presenter.CreditPresenterImpl;
import com.tome.main.view.CreditView;
import com.tome.modulebase.BaseFragment;
import com.tome.modulebase.Constants;

import butterknife.BindView;

/**
 * Created by zhangyufei
 */
public class ListFragment extends BaseFragment implements CreditView {
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private CreditListAdapter mCreditListAdapter;

    private CreditPresenter mCreditPresenter;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_list;
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mCreditListAdapter = new CreditListAdapter(getContext(), mCreditPresenter.getCreditList());
        mCreditListAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mCreditListAdapter);
    }

    @Override
    protected void init() {
        super.init();
        mCreditPresenter = new CreditPresenterImpl(this);
        initRecyclerView();
    }
    private CreditListAdapter.OnItemClickListener mOnItemClickListener = new CreditListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(String name) {
            // 简单跳转
            Router.build("chat").with(Constants.Extra.USER_NAME, name).go(getActivity());

        }

        @Override
        public void onItemLongClick(String name) {

        }
    };

    @Override
    public void onGetCreditListSuccess() {
        mCreditListAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetCreditListFailed() {

    }
}
