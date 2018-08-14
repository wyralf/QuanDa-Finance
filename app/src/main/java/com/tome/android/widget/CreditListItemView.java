package com.tome.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tome.android.Image.config.ScaleMode;
import com.tome.android.Image.loader.ImageLoader;
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
    @BindView(R.id.info_list)
    LinearLayout mListLayout;
    @BindView(R.id.name)
    TextView mCreditName;
    @BindView(R.id.limit)
    TextView mCreditLimit;
    @BindView(R.id.interest)
    TextView mCreditInterest;
    Context mContext;
    public CreditListItemView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CreditListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.list_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(CreditItem creditItem) {
        mCreditName.setText(creditItem.mCreditName);
        mCreditLimit.setText(creditItem.mCreditLimit);
        mCreditInterest.setText(creditItem.mCreditInterest);
        ImageLoader.with(mContext)
                .url(creditItem.mImageUrl)
                .placeHolder(R.mipmap.avatar1)
                .scale(ScaleMode.FIT_CENTER)
                .into(mAvatar);
    }
}
