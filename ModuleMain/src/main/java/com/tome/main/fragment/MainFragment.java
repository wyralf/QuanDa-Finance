package com.tome.main.fragment;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.oragee.banners.BannerView;
import com.tome.main.R;
import com.tome.main.R2;
import com.tome.modulebase.control.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainFragment extends BaseFragment{
    @BindView(R2.id.bv)
    BannerView mBannerView;
    @BindView(R2.id.gv)
    GridView mGridView;

    List<View> viewList;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init() {
        super.init();
        initViewList();
        initBannerView();
    }

    private void initViewList() {
        viewList = new ArrayList<View>();
        for (int i = 0; i < 3; i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.mipmap.avatar1);
            viewList.add(imageView);
        }
    }
    private void initBannerView() {
        mBannerView.setViewList(viewList);
        mBannerView.startLoop(true);
    }
}
