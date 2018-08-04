package com.tome.android.view;

/**
 * Created by zhangyufei
 */
public interface ChatView {

    void onStartSendMessage();

    void onSendMessageSuccess();

    void onSendMessageFailed();

    void onMessagesLoaded();

    void onMoreMessagesLoaded(int size);

    void onNoMoreData();
}
