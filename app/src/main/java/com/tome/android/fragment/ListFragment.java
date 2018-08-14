package com.tome.android.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tome.android.R;
import com.tome.android.activity.ChatActivity;
import com.tome.android.adapter.CreditListAdapter;
import com.tome.android.app.Constants;
import com.tome.android.presenter.CreditPresenter;
import com.tome.android.presenterimpl.CreditPresenterImpl;
import com.tome.android.view.CreditView;

import butterknife.BindView;

/**
 * Created by zhangyufei
 */
public class ListFragment extends BaseFragment implements CreditView{
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
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
            startActivity(ChatActivity.class, Constants.Extra.USER_NAME, name);
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
