package com.tome.android.presenterimpl;

import com.tome.android.adapter.CreditListAdapter;
import com.tome.android.model.CreditItem;
import com.tome.android.presenter.CreditPresenter;
import com.tome.android.view.CreditView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyufei
 */
public class CreditPresenterImpl implements CreditPresenter {
    private CreditView mCreditView;

    private List<CreditItem> mCreditListItems;
    @Override
    public List<CreditItem> getCreditList() {
        return mCreditListItems;
    }

    @Override
    public void refreshCreditList() {
        mCreditListItems.clear();
        getCreditsFromServer();
    }

    @Override
    public void getCreditsFromServer() {
        if (mCreditListItems.size() > 0) {
            mCreditView.onGetCreditListSuccess();
            return;
        }
    }

    public CreditPresenterImpl(CreditView creditView) {
        mCreditView = creditView;
        mCreditListItems = new ArrayList<CreditItem>();
    }

    private void saveContactToDatabase(String creditName) {

    }
}
