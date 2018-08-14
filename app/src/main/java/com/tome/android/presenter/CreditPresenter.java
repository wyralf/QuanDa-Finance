package com.tome.android.presenter;

import com.tome.android.model.CreditItem;

import java.util.List;

/**
 * Created by zhangyufei
 */
public interface CreditPresenter {

    List<CreditItem> getCreditList();

    void refreshCreditList();

    void getCreditsFromServer();
}
