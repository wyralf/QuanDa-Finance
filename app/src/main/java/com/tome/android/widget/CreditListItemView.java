package com.tome.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tome.android.R;
import com.tome.android.model.CreditItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangyufei
 */
public class CreditListItemView extends RelativeLayout {
    public static final String TAG = "ListItemView";
    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.name)
    TextView mCreditName;
    public CreditListItemView(Context context) {
        super(context);
    }

    public CreditListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.list_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(CreditItem creditItem) {
        mCreditName.setText(creditItem.mCreditName);
    }
}
