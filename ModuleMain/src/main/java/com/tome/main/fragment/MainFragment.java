package com.tome.main.fragment;

import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

import com.oragee.banners.BannerView;
import com.tome.main.R;
import com.tome.main.R2;
import com.tome.modulebase.control.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MainFragment extends BaseFragment{
    @BindView(R2.id.bv)
    BannerView mBannerView;
    @BindView(R2.id.gv)
    GridView mGridView;
    @BindView(R2.id.main_btn)
    Button mButton;

    List<View> viewList;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init() {
        super.init();
        initViewList();
        initBannerView();
        initData();
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

    void initData() {
        //图标
        int icno = R.mipmap.avatar1;
        //图标下的文字
        String name[]={"时钟","信号","宝箱","秒钟","大象","FF","记事本","书签","印象","商店","主题","迅雷"};
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <name.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("img", icno);
            map.put("text",name[i]);
            dataList.add(map);
        }
        String[] from={"img","text"};

        int[] to={R.id.hank_img,R.id.hank_text};

        adapter=new SimpleAdapter(getActivity(), dataList, R.layout.hank_item, from, to);

        mGridView.setAdapter(adapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                builder.setTitle("提示").setMessage(dataList.get(arg2).get("text").toString()).create().show();
            }
        });
    }
}
