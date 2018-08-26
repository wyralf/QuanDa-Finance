package com.tome.main.presenter;

import com.tome.main.model.CreditItem;

import java.util.List;

/**
 * Created by zhangyufei
 */
public interface CreditPresenter {

    List<CreditItem> getCreditList();

    void refreshCreditList();

    void getCreditsFromServer();
}
