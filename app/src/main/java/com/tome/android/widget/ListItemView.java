package com.tome.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tome.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangyufei
 */
public class ListItemView extends RelativeLayout {
    public static final String TAG = "ListItemView";
    public ListItemView(Context context) {
        super(context);
    }

    public ListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.list_item, this);
        ButterKnife.bind(this, this);
    }
}
