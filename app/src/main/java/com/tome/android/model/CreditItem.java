package com.tome.android.model;

import com.tome.android.R;

/**
 * Created by zhangyufei
 * 贷款实体类
 */
public class CreditItem {
    public int avatar = R.mipmap.avatar6;

    public String mCreditName;

    public boolean showFirstLetter = true;

    public char getFirstLetter() {
        return mCreditName.charAt(0);
    }

    public String getFirstLetterString() {
        return String.valueOf(getFirstLetter()).toUpperCase();
    }
}
