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
        startGetCreditList();
    }

    public CreditPresenterImpl(CreditView creditView) {
        mCreditView = creditView;
        mCreditListItems = new ArrayList<CreditItem>();
        getCreditsFromServer();
    }

    private void startGetCreditList(){
        for (int i = 0; i < 20; i++) {
            CreditItem item = new CreditItem();
            item.mCreditName = "科比";
            item.mImageUrl = "http://s1.dwstatic.com/group1/M00/86/4A/81beb00a44bc52b4fdd46285de8f8f00.png";
            mCreditListItems.add(item);
            saveContactToDatabase(item.mCreditName);
        }
    }

    private void saveContactToDatabase(String creditName) {

    }
}
