package com.tome.android.activity;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.tome.android.R;
import com.tome.modulebase.control.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by zhangyufei
 */
public class GuideActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    /**装分页显示的view的数组*/
    private ArrayList<View> pageViews;
    @Override
    public int getLayoutRes() {
        return R.layout.activity_guide;
    }

    @Override
    protected void init() {
        super.init();
        pageViews = new ArrayList<View>();
        //设置viewpager的适配器和监听事件
        viewPager.setAdapter(new GuidePageAdapter());
    }

    class GuidePageAdapter extends PagerAdapter {

        //销毁position位置的界面
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(pageViews.get(position));
        }

        //获取当前窗体界面数
        @Override
        public int getCount() {
            return pageViews.size();
        }

        //初始化position位置的界面
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(pageViews.get(position));
            return pageViews.get(position);
        }

        // 判断是否由对象生成界面
        @Override
        public boolean isViewFromObject(View v, Object arg1) {
            return v == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }

}
